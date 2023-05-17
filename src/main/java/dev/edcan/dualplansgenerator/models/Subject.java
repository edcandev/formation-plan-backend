package dev.edcan.dualplansgenerator.models;

public class Subject {

    private String subjectId;
    private String period;
    private String partial;
    private boolean isValid;

    public Subject() {
    }
    public Subject withSubjectId(String subjectId) {
        this.subjectId = subjectId;
        return this;
    }
    public Subject withPeriod(String period) {
        this.period = period;
        return this;
    }
    public Subject withPartial(String partial) {
        this.partial = partial;
        return this;
    }
    public Subject withValid(boolean isValid) {
        this.isValid = isValid;
        return this;
    }
    public Subject build() { return this; }

    public String getSubjectId() {
        return subjectId;
    }
    public String getPeriod() {
        return period;
    }
    public String getPartial() {
        return partial;
    }
    public boolean isValid() {
        return isValid;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId='" + subjectId + '\'' +
                ", period='" + period + '\'' +
                ", partial='" + partial + '\'' +
                ", isValid=" + isValid +
                '}';
    }
}
