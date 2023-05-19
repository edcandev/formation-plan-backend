package dev.edcan.dualplansgenerator.services;

import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

public interface IFileDownloadService {
    File getReportsFileByStudentId(String studentId);

}
