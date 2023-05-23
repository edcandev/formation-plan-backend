package dev.edcan.dualplansgenerator.repositories;

import dev.edcan.dualplansgenerator.models.IEMentor;
import dev.edcan.dualplansgenerator.services.IExcelManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class IEMentorsRepositoryImpl implements IIEMentorsRepository {

    @Autowired
    IExcelManagementService excelManagementService;

    @Override
    public List<IEMentor> getIEMentors() {
        return excelManagementService.getIEMentors();
    }

    @Override
    public IEMentor getMentorByFullName(String fullName) {
        List<IEMentor> ieMentorList = excelManagementService.getIEMentors();
        IEMentor mentor = ieMentorList.stream().filter(ieMentor -> {
            return Objects.equals(ieMentor.getFullName(), fullName);
        }).toList().get(0);
        return mentor;
    }
}
