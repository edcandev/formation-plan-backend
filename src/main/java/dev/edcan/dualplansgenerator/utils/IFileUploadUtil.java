package dev.edcan.dualplansgenerator.utils;

import java.nio.file.Path;

public interface IFileUploadUtil {
    Path getDataFolderPath(String fileName);
    Path getDataFilePath(String fileName);
    Path getReportFolderPath(String fileName);

}
