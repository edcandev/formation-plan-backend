package dev.edcan.dualplansgenerator.controllers;

import dev.edcan.dualplansgenerator.models.IEMentor;
import dev.edcan.dualplansgenerator.models.PlanGeneratorRequest;
import dev.edcan.dualplansgenerator.models.PlanGeneratorResponse;
import dev.edcan.dualplansgenerator.models.StudentExcelResponse;
import dev.edcan.dualplansgenerator.services.*;
import dev.edcan.dualplansgenerator.utils.IFileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.SQLOutput;
import java.util.List;

@RestController
@CrossOrigin("*")
public class AppController {

    @Autowired
    IFileUploadService fileUploadService;
    @Autowired
    IFileUploadUtil fileUploadUtil;
    @Autowired
    IDLLExecutorService executorService;
    @Autowired
    IExcelManagementService excelManagementService;
    @Autowired
    IFileDownloadService fileDownloadService;
    @Autowired
    IExcelValidatorService excelValidatorService;

    @PostMapping("/uploadFile")
    public ResponseEntity<StudentExcelResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("mentor") String mentor) throws IOException {


        String fileName = multipartFile.getOriginalFilename();
        String studentId = fileUploadUtil.getStudentIdByFileName(fileName);

        System.out.println("NOMBRE DEL ARCHIVO: " + multipartFile.getOriginalFilename());
        System.out.println("NOMBRE DEL MENTOR: " + mentor);

        if(! excelValidatorService.isAValidFileName(multipartFile.getOriginalFilename())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(! excelValidatorService.canGeneratePlan(mentor, studentId)) return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        fileUploadService.saveFile(multipartFile);

        if(! excelManagementService.existsByFilename(fileName)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        StudentExcelResponse response = excelManagementService.getStudentExcelInfo(multipartFile.getOriginalFilename());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/generatePlan", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanGeneratorResponse> generatePlan(@RequestBody(required = true) PlanGeneratorRequest requestBody) {

        PlanGeneratorResponse response = new PlanGeneratorResponse();
        if(executorService.generatePlan(requestBody)) {
            executorService.zipPlan(requestBody.getStudentId());
            response.setWasGenerated(true);

        } else {
            response.setWasGenerated(false);
        }
        response.setStudentId(requestBody.getStudentId());
        response.setFileName(requestBody.getStudentFileName());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/downloadPlan/{studentId}.zip")
    public ResponseEntity<Resource> downloadPlan(@PathVariable String studentId) {

        File userZipFile = fileDownloadService.getReportsFileByStudentId(studentId);

        System.out.println(userZipFile);

        try {

            System.out.println("REPORTE GENERADO PARA: " + userZipFile);

            InputStreamResource resource = new InputStreamResource(new FileInputStream(userZipFile));
            return ResponseEntity.ok()
                    .contentLength(userZipFile.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch(FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("No existe el recurso...");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/getMentors")
    public ResponseEntity<List<IEMentor>> getMentors() {
        return new ResponseEntity<>(excelManagementService.getIEMentors(),HttpStatus.OK);
    }
}
