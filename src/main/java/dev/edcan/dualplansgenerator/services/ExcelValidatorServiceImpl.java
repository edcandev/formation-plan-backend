package dev.edcan.dualplansgenerator.services;

import org.springframework.stereotype.Service;

@Service
public class ExcelValidatorServiceImpl implements IExcelValidatorService {

    @Override
    public boolean canGeneratePlan() {
        return false;
    }
}
