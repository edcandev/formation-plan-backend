package dev.edcan.dualplansgenerator.services;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface IFileUploadService {
    Path getFolderPath(String filename);

    void saveFile(String fileName, MultipartFile multipartFile) throws IOException;
}
