package dev.edcan.dualplansgenerator.models;


import java.util.*;

public class IEMentor {

    private String fullName;

    private Set<String> studentsIds;

    public IEMentor() {
        studentsIds = new HashSet<>();
    }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public Set<String> getStudentsIds() { return studentsIds; }
    public void setStudentsIds(Set<String> studentsIds) { this.studentsIds = studentsIds; }

    public void addStudentId(String studentId) {
        Set<String> tempList = getStudentsIds();
        tempList.add(studentId);
        setStudentsIds(tempList);
    }

    @Override
    public String toString() {
        return "IEMentor{" +
                "fullName='" + fullName + '\'' +
                ", studentsIds=" + studentsIds +
                '}';
    }
}
