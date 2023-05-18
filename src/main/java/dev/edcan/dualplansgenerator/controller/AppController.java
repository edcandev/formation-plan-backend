package dev.edcan.dualplansgenerator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.edcan.dualplansgenerator.models.Materias;
import dev.edcan.dualplansgenerator.models.PlanGeneratorRequest;
import dev.edcan.dualplansgenerator.models.PlanGeneratorResponse;
import dev.edcan.dualplansgenerator.models.StudentExcelResponse;
import dev.edcan.dualplansgenerator.repositories.MateriasRepositoryImpl;
import dev.edcan.dualplansgenerator.services.FileDownloadServiceImpl;
import dev.edcan.dualplansgenerator.services.IDLLExecutorService;
import dev.edcan.dualplansgenerator.services.IExcelManagementService;
import dev.edcan.dualplansgenerator.services.IFileUploadService;
import dev.edcan.dualplansgenerator.utils.IFileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

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
    FileDownloadServiceImpl fileDownloadService;

    @PostMapping("/uploadFile")
    public ResponseEntity<StudentExcelResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("mentor") String mentor) throws IOException {

        //String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        fileUploadService.saveFile(multipartFile);

        if(! excelManagementService.existsByFilename(multipartFile.getOriginalFilename())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


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

        File userZipFile = fileDownloadService.getFileByStudentId(studentId);

        System.out.println(userZipFile);

        try {

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
}
