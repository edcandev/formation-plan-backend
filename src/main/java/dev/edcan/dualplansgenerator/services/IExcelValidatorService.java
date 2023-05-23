package dev.edcan.dualplansgenerator.services;

public interface IExcelValidatorService {
    boolean canGeneratePlan(String ieMentorName, String studentId);
}
