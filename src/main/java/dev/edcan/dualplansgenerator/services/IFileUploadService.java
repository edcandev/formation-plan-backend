package dev.edcan.dualplansgenerator.services;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface IFileUploadService {
    void saveFile(MultipartFile multipartFile);
    void deleteFileDirectory(MultipartFile multipartFile);
}
