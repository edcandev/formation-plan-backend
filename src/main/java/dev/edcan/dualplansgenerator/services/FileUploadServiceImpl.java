package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.utils.IFileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    IFileUploadUtil fileUploadUtil;

    public void saveFile(String fileName, MultipartFile multipartFile) throws IOException {

        // Dual/data/{matricula}
        Path uploadPath = fileUploadUtil.getDataFolderPath(fileName);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        //String fileCode = RandomStringUtils.randomAlphanumeric(8);
        String fileCode = multipartFile.getOriginalFilename();


        Path directory = Paths.get("Dual/data");
        //String folderPathString = directory.toFile().getAbsolutePath();
        String home = System.getProperty("user.home");
        String folderPathString = home + "/Desktop/Dual/data";
        Path pathExterno = Paths.get(folderPathString).toAbsolutePath();
        byte[] bytes = multipartFile.getBytes();
        //Path filePath = Paths.get(folderPathString.concat("/").concat(fileName));
        Path filePath = pathExterno.resolve(fileName.substring(0,9)).resolve(fileName);

        Path hardcoded = Paths.get(home,"Desktop","Dual","data", multipartFile.getOriginalFilename());
        // Path hardcoded = Paths.get("opt","Dual","data", multipartFile.getOriginalFilename());
        Files.write(hardcoded, bytes);


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
