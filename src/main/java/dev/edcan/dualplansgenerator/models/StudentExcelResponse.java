package dev.edcan.dualplansgenerator.models;

import java.util.List;

public class StudentExcelResponse {

    private String studentId;
    private String firstSurname;
    private String lastSurname;
    private String name;
    private String ieMentor;
    private List<Subject> subjectList;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getLastSurname() {
        return lastSurname;
    }

    public void setLastSurname(String lastSurname) {
        this.lastSurname = lastSurname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIeMentor() {
        return ieMentor;
    }

    public void setIeMentor(String ieMentor) {
        this.ieMentor = ieMentor;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    @Override
    public String toString() {
        return "StudentExcelResponse{" +
                "studentId='" + studentId + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", lastSurname='" + lastSurname + '\'' +
                ", name='" + name + '\'' +
                ", ieMentor='" + ieMentor + '\'' +
                ", subjectList=" + subjectList +
                '}';
    }
}
