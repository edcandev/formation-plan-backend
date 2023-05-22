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

    @Autowired
    IFileUploadUtil fileUploadUtil;

    public void saveFile(MultipartFile multipartFile) {

        String fileName = multipartFile.getOriginalFilename();
        assert fileName != null;

        Path projectPath = fileUploadUtil.getGeneratorProjectPath();

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

        //Path pathExterno = fileUploadUtil.getGeneratorProjectPath();
        //byte[] bytes = multipartFile.getBytes();
        //Path filePath = Paths.get(folderPathString.concat("/").concat(fileName));
        //Path filePath = pathExterno.resolve(fileName.substring(0,9)).resolve(fileName);

        //Path hardcoded = Paths.get(home,"Desktop","Dual","data", multipartFile.getOriginalFilename());
        // Path hardcoded = Paths.get("opt","Dual","data", multipartFile.getOriginalFilename());
        //Files.write(hardcoded, bytes);

        /*
        try (InputStream inputStream = multipartFile.getInputStream()) {
            // Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
            Path filePath = uploadPath.resolve(fileName);

            System.out.println(filePath);
            System.out.println(Path.of("Users","developer","Desktop","201920732.xlsx"));
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("NO SE PUDO GUARDAR: " + fileName, e);
        }*/
    }

    @Override
    public void deleteFileDirectory(String fileName) {

    }

}
