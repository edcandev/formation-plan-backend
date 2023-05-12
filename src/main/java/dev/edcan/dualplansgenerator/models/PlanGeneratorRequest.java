package dev.edcan.dualplansgenerator.models;

import java.util.Date;

public class PlanGeneratorRequest {
    private String studentId;
    private String studentFileName;
    private Date generationDate;
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

    public Date getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(Date generationDate) {
        this.generationDate = generationDate;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setStudentFileName(String studentFileName) {
        this.studentFileName = studentFileName;
    }
}
