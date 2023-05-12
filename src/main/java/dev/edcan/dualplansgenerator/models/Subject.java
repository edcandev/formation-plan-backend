package dev.edcan.dualplansgenerator.models;

public class Subject {
    private String subjectId;
    private String period;
    private String partial;

    public Subject() {
    }
    public Subject(String subjectId, String period, String partial) {
        this.subjectId = subjectId;
        this.period = period;
        this.partial = partial;
    }
    public String getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
    public String getPeriod() {
        return period;
    }
    public void setPeriod(String period) {
        this.period = period;
    }
    public String getPartial() {
        return partial;
    }
    public void setPartial(String partial) {
        this.partial = partial;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId='" + subjectId + '\'' +
                ", period='" + period + '\'' +
                ", partial='" + partial + '\'' +
                '}';
    }
}
