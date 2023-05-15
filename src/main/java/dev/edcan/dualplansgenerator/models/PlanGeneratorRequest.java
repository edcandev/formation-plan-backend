package dev.edcan.dualplansgenerator.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PlanGeneratorRequest {
    private String studentId;
    private String studentFileName;
    private String generationDateString;
    private String period;

    public PlanGeneratorRequest() {
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentFileName() {
        return studentFileName;
    }

    public void setStudentFileName(String studentFileName) {
        this.studentFileName = studentFileName;
    }

    public String getGenerationDateString() {
        return generationDateString;
    }

    public void setGenerationDateString(String generationDateString) {
        this.generationDateString = generationDateString;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "PlanGeneratorRequest{" +
                "studentId='" + studentId + '\'' +
                ", studentFileName='" + studentFileName + '\'' +
                ", generationDateString='" + generationDateString + '\'' +
                ", period='" + period + '\'' +
                '}';
    }
}
