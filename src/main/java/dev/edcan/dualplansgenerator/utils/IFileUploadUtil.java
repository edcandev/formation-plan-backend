package dev.edcan.dualplansgenerator.utils;

import java.nio.file.Path;

public interface IFileUploadUtil {
    String getStudentIdByFileName(String fileName);
    Path getGeneratorProjectPath();
    Path getDataFolderPath(String fileName);
    Path getStudentReportFolderPath(String fileName);
    Path getReportFolderPath();

}
