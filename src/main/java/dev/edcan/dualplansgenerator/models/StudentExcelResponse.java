package dev.edcan.dualplansgenerator.models;

import java.util.List;

public class StudentExcelResponse {

    private String studentId;
    private String firstSurname;
    private String lastSurname;
    private String name;
    private String ieMentor;
    private String fileName;
    private List<Subject> subjectList;


    public StudentExcelResponse () {

    }

    public StudentExcelResponse withStudentId(String studentId) {
        this.studentId = studentId;
        return this;
    }
    public StudentExcelResponse withFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
        return this;
    }
    public StudentExcelResponse withLastSurname(String lastSurname) {
        this.lastSurname = lastSurname;
        return this;
    }
    public StudentExcelResponse withName(String name) {
        this.name = name;
        return this;
    }
    public StudentExcelResponse withfileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
    public StudentExcelResponse withIeMentor(String ieMentor) {
        this.ieMentor = ieMentor;
        return this;
    }
    public StudentExcelResponse withSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
        return this;
    }
    public StudentExcelResponse build() {
        return this;
    }

    public String getStudentId() {
        return studentId;
    }
    public String getFirstSurname() {
        return firstSurname;
    }
    public String getLastSurname() {
        return lastSurname;
    }
    public String getName() {
        return name;
    }
    public String getFileName() {
        return fileName;
    }
    public String getIeMentor() {
        return ieMentor;
    }
    public List<Subject> getSubjectList() {
        return subjectList;
    }

    @Override
    public String toString() {
        return "StudentExcelResponse{" +
                "studentId='" + studentId + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", lastSurname='" + lastSurname + '\'' +
                ", name='" + name + '\'' +
                ", ieMentor='" + ieMentor + '\'' +
                ", fileName='" + fileName + '\'' +
                ", subjectList=" + subjectList +
                '}';
    }
}
