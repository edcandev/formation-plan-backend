package dev.edcan.dualplansgenerator.controller;

import dev.edcan.dualplansgenerator.models.FileUploadResponse;
import dev.edcan.dualplansgenerator.models.StudentExcelResponse;
import dev.edcan.dualplansgenerator.services.CommandsRunnerService;
import dev.edcan.dualplansgenerator.services.ExcelManagementService;
import dev.edcan.dualplansgenerator.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        //long size = multipartFile.getSize();
        //String fileCode = fileUploadUtil.saveFile(fileName, multipartFile);
        StudentExcelResponse response = excelManagementService.getStudentExcelInfo(fileName);
        /*FileUploadResponse response = new FileUploadResponse();
        response.setDownloadUri("/downloadFile/" + fileCode);*/
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
