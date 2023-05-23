package dev.edcan.dualplansgenerator.services;

import dev.edcan.dualplansgenerator.models.IEMentor;
import dev.edcan.dualplansgenerator.repositories.IIEMentorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ExcelValidatorServiceImpl implements IExcelValidatorService {

    @Autowired
    IIEMentorsRepository ieMentorsRepository;

    @Override
    public boolean canGeneratePlan(String ieMentorName, String studentId) {
        IEMentor ieMentor = ieMentorsRepository.getMentorByFullName(ieMentorName);
        return ieMentor.getStudentsIds().contains(studentId);
    }

    @Override
    public boolean isAValidFileName(String fileName) {
        String name = fileName.substring(0,9);
        Pattern pattern = Pattern.compile("\\d{9}");
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }
}
