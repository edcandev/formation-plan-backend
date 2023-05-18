package dev.edcan.dualplansgenerator.models;

public class PlanGeneratorResponse {

    private boolean wasGenerated;
    private String downloadLink;
    private String fileName;
    private String studentId;

    public PlanGeneratorResponse() {
    }
    public PlanGeneratorResponse(boolean wasGenerated) {
        this.wasGenerated = wasGenerated;
    }

    public PlanGeneratorResponse(boolean wasGenerated, String downloadLink, String fileName, String studentId) {
        this.wasGenerated = wasGenerated;
        this.downloadLink = downloadLink;
        this.fileName = fileName;
        this.studentId = studentId;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public boolean isWasGenerated() {
        return wasGenerated;
    }

    public void setWasGenerated(boolean wasGenerated) {
        this.wasGenerated = wasGenerated;
    }
}
