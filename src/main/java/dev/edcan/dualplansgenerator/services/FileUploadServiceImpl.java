package dev.edcan.dualplansgenerator.services;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadServiceImpl  implements IFileUploadService {

    public Path getFolderPath(String fileName) {
        String folderName = fileName.substring(0,9);
        return Paths.get("Dual","data", folderName);
    }

    public String saveFile(String fileName, MultipartFile multipartFile) throws IOException {

        Path uploadPath = getFolderPath(fileName);

        //System.out.println(uploadPath);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        //String fileCode = RandomStringUtils.randomAlphanumeric(8);
        String fileCode = multipartFile.getOriginalFilename();

        try (InputStream inputStream = multipartFile.getInputStream()) {
            // Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("NO SE PUDO GUARDAR: " + fileName, e);
        }
        return fileCode;
    }

}
