package dev.edcan.dualplansgenerator.repositories;

import dev.edcan.dualplansgenerator.models.IEMentor;
import dev.edcan.dualplansgenerator.services.IExcelManagementService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IEMentorsRepositoryImpl implements IIEMentorsRepository {

    IExcelManagementService excelManagementService;

    @Override
    public List<IEMentor> getIEMentors() {
        excelManagementService.getIEMentors();
        return null;
    }
}
