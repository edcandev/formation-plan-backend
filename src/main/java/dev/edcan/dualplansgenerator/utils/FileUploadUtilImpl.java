package dev.edcan.dualplansgenerator.utils;

import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUploadUtilImpl implements IFileUploadUtil {

    @Override
    public Path getDataFolderPath(String fileName) {
        return Paths.get("Dual","data", fileName.substring(0,9));
    }

    @Override
    public Path getDataFilePath(String fileName) {
        return Paths.get(String.valueOf(getDataFolderPath(fileName).toUri()),fileName);
    }

    @Override
    public Path getReportFolderPath(String fileName) {
        return Paths.get("Dual","Reports", fileName.substring(0,9));
    }
}
