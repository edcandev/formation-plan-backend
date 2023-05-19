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
public class FileDownloadServiceImpl implements IFileDownloadService {

    Path foundFile;
    @Autowired
    IFileUploadUtil fileUploadUtil;

    @Override
    public File getReportsFileByStudentId(String studentId) {

        Path zipFilePath = fileUploadUtil.getGeneratorProjectPath().resolve("Reports").resolve(studentId.concat(".zip"));
        File zipFile = zipFilePath.toFile();

        //System.out.println(zipFile);

        if(! Files.exists(zipFile.toPath())) return null;

        return zipFile;
    }
}
