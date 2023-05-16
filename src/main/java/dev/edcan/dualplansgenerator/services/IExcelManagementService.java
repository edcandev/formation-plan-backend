package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.models.StudentExcelResponse;

public interface IExcelManagementService {
    StudentExcelResponse getStudentExcelInfo(String fileCode);
    boolean existsByFilename(String filecode);
    boolean isAValidSubject(String subjectId);

}
