package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.models.IEMentor;
import dev.edcan.dualplansgenerator.repositories.IIEMentorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcelValidatorServiceImpl implements IExcelValidatorService {

    @Autowired
    IIEMentorsRepository ieMentorsRepository;

    @Override
    public boolean canGeneratePlan(String ieMentorName, String studentId) {
        IEMentor ieMentor = ieMentorsRepository.getMentorByFullName(ieMentorName);
        return ieMentor.getStudentsIds().contains(studentId);
    }
}
