package dev.edcan.dualplansgenerator.controller;

import dev.edcan.dualplansgenerator.models.PlanGeneratorRequest;
import dev.edcan.dualplansgenerator.models.StudentExcelResponse;
import dev.edcan.dualplansgenerator.services.CommandsRunnerService;
import dev.edcan.dualplansgenerator.services.ExcelManagementService;
import dev.edcan.dualplansgenerator.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@RestController
@CrossOrigin("*")
public class FileUploadController {

    @Autowired
    FileUploadUtil fileUploadUtil;
    @Autowired
    CommandsRunnerService commandsRunnerService;
    @Autowired
    ExcelManagementService excelManagementService;

    @PostMapping("/uploadFile")
    public ResponseEntity<StudentExcelResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("mentor") String mentor) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        //long size = multipartFile.getSize();
        String fileCode = fileUploadUtil.saveFile(fileName, multipartFile);
        //System.out.println(fileCode);
        StudentExcelResponse response = excelManagementService.getStudentExcelInfo(fileCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/generatePlan", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generatePlan(@RequestBody(required = true) PlanGeneratorRequest body) {
        System.out.println("petición de tipo get para generar");

        String studentFileName = body.getStudentFileName();
        String studentId = body.getStudentId();




        return new ResponseEntity<>("Planes generados", HttpStatus.OK);
    }
}
