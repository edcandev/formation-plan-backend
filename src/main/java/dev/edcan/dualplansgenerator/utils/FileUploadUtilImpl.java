package dev.edcan.dualplansgenerator.utils;

import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUploadUtilImpl implements IFileUploadUtil {

    @Override
    public String getStudentIdByFileName(String fileName) {
        return fileName.substring(0,9);
    }

    @Override
    public Path getGeneratorProjectPath() { // HOME/Dual
        String projectDirectoryPath = System.getProperty("user.home") + "/Dual";
        return Paths.get(projectDirectoryPath).toAbsolutePath();
    }

    @Override
    public Path getDataFolderPath(String fileName) {
        return getGeneratorProjectPath().resolve("data").resolve(fileName.substring(0, 9));
        //return Paths.get("Dual","data", fileName.substring(0,9));
    }

    @Override
    public Path getReportFolderPath(String fileName) {
        return getGeneratorProjectPath().resolve("Reports").resolve(fileName.substring(0, 9));
    }
}
