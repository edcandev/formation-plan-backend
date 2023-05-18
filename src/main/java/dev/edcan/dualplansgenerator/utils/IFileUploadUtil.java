package dev.edcan.dualplansgenerator.utils;

import java.nio.file.Path;

public interface IFileUploadUtil {
    Path getGeneratorProjectPath();
    Path getDataFolderPath(String fileName);
    Path getReportFolderPath(String fileName);

}
