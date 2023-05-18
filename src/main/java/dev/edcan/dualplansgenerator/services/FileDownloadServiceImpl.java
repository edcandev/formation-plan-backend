package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.utils.IFileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileDownloadServiceImpl {

    Path foundFile;
    @Autowired
    IFileUploadUtil fileUploadUtil;

    public Resource getFileAsResource(String fileCode) throws IOException {
        Path dirPath = Paths.get("Files-Upload");

        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile = file;
                return;
            }
        });

        if (foundFile != null) {
            return new UrlResource(foundFile.toUri());
        }
        return null;
    }

    public File getFileByStudentId(String studentId) {

        Path zipFilePath = fileUploadUtil.getGeneratorProjectPath().resolve("Reports").resolve(studentId.concat(".zip"));
        File zipFile = zipFilePath.toFile();

        //System.out.println(zipFile);

        if(! Files.exists(zipFile.toPath())) return null;

        return zipFile;
    }
}
