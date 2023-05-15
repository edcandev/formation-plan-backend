package dev.edcan.dualplansgenerator.controller;

import dev.edcan.dualplansgenerator.models.PlanGeneratorRequest;
import dev.edcan.dualplansgenerator.models.StudentExcelResponse;
import dev.edcan.dualplansgenerator.services.DLLExecutorServiceImpl;
import dev.edcan.dualplansgenerator.services.IDLLExecutorService;
import dev.edcan.dualplansgenerator.services.IExcelManagementService;
import dev.edcan.dualplansgenerator.services.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin("*")
public class AppController {

    @Autowired
    IFileUploadService fileUploadService;
    @Autowired
    IDLLExecutorService executorService;
    @Autowired
    IExcelManagementService excelManagementService;

    @PostMapping("/uploadFile")
    public ResponseEntity<StudentExcelResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile, @RequestParam("mentor") String mentor) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        //long size = multipartFile.getSize();
        String fileCode = fileUploadService.saveFile(fileName, multipartFile);
        //System.out.println(fileCode);
        StudentExcelResponse response = excelManagementService.getStudentExcelInfo(fileCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/generatePlan", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanGeneratorRequest> generatePlan(@RequestBody(required = true) PlanGeneratorRequest requestBody) {
        System.out.println("petición de tipo get para generar");

        executorService.generatePlan(requestBody);

        // Implementar un metodo que verifi ue la existencia




        return new ResponseEntity<>(requestBody, HttpStatus.OK);
    }
}
