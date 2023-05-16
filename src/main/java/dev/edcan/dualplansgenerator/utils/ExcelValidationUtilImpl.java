package dev.edcan.dualplansgenerator.utils;

import org.springframework.stereotype.Component;

@Component
public class ExcelValidationUtilImpl implements IExcelValidationUtil {

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public boolean isAValidSubject() {
        return false;
    }
}
