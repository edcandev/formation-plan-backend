package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.utils.IFileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadServiceImpl  implements IFileUploadService {

    IFileUploadUtil fileUploadUtil;

    static Path projectPath;

    @Autowired
    FileUploadServiceImpl(IFileUploadUtil fileUploadUtil) {
        projectPath = fileUploadUtil.getGeneratorProjectPath();
    }

    public void saveFile(MultipartFile multipartFile) {

        String fileName = multipartFile.getOriginalFilename();
        assert fileName != null;

        // Path projectPath = fileUploadUtil.getGeneratorProjectPath();

        Path userFileFolderPath = projectPath.resolve("data").resolve(fileName.substring(0,9));


        try {

            if (! Files.exists(userFileFolderPath)) { Files.createDirectories(userFileFolderPath); }

            byte[] fileBytes = multipartFile.getBytes();
            Path userFilePath = userFileFolderPath.resolve(fileName);

            if(! Files.exists(userFilePath)) {

                System.out.println("==================== SE HA CARGADO EL ARCHIVO: ".concat(userFilePath.toString()).concat(" ===================="));
                Files.write(userFilePath,fileBytes);
            }

        } catch (IOException e) {
            System.out.println("==================== ERROR AL SUBIR ARCHIVO DE:".concat(fileName).concat(" ===================="));
        }
    }

    @Override
    public void deleteFileDirectory(MultipartFile multipartFile) {

        String fileName = multipartFile.getOriginalFilename();
        assert fileName != null;
        Path userFileFolderPath = projectPath.resolve("data").resolve(fileName.substring(0,9));
        Path userFilePath = projectPath.resolve("data").resolve(fileName.substring(0,9)).resolve(fileName);

        try {

            Files.deleteIfExists(userFilePath);
            Files.deleteIfExists(userFileFolderPath);
            System.out.println("ARCHIVO ELIMINADO: " + userFileFolderPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
