package dev.edcan.dualplansgenerator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.edcan.dualplansgenerator.models.Materias;
import dev.edcan.dualplansgenerator.models.PlanGeneratorRequest;
import dev.edcan.dualplansgenerator.models.StudentExcelResponse;
import dev.edcan.dualplansgenerator.repositories.MateriasRepositoryImpl;
import dev.edcan.dualplansgenerator.services.IDLLExecutorService;
import dev.edcan.dualplansgenerator.services.IExcelManagementService;
import dev.edcan.dualplansgenerator.services.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

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

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        fileUploadService.saveFile(fileName, multipartFile);

        if(! excelManagementService.existsByFilename(fileName)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        //System.out.println(excelManagementService.existsByFilename(fileName));

        StudentExcelResponse response = excelManagementService.getStudentExcelInfo(fileName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/generatePlan", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlanGeneratorRequest> generatePlan(@RequestBody(required = true) PlanGeneratorRequest requestBody) {
        System.out.println("petición de tipo get para generar");

        // executorService.generatePlan(requestBody);


        System.out.println();


        // Implementar un metodo que verifi ue la existencia




        return new ResponseEntity<>(requestBody, HttpStatus.OK);
    }
}
