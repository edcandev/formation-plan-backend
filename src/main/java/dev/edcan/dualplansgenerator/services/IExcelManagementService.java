package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.models.IEMentor;
import dev.edcan.dualplansgenerator.models.StudentExcelResponse;
import dev.edcan.dualplansgenerator.utils.IFileUploadUtil;

import java.util.Collection;
import java.util.List;

public interface IExcelManagementService {
    StudentExcelResponse getStudentExcelInfo(String fileName);

    List<IEMentor> getIEMentors();
    boolean existsByFilename(String filecode);
    boolean isAValidSubject(String subjectId);

}
