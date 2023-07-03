package dev.edcan.dualplansgenerator.services;

import java.io.IOException;

public interface IFIleRemoveService {
    boolean deleteReportFolderByStudentId(String studentId) throws IOException;
}
