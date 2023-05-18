package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.models.StudentExcelResponse;

public interface IExcelManagementService {
    StudentExcelResponse getStudentExcelInfo(String fileName);
    boolean existsByFilename(String filecode);
    boolean isAValidSubject(String subjectId);

}
