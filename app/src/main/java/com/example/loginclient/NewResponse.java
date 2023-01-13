package com.example.loginclient;

public class NewResponse {

    private String field;

    private String studentName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMailId() {
        return studentMailId;
    }

    public void setStudentMailId(String studentMailId) {
        this.studentMailId = studentMailId;
    }

    public String getStudentContactNumber() {
        return studentContactNumber;
    }

    public void setStudentContactNumber(String studentContactNumber) {
        this.studentContactNumber = studentContactNumber;
    }

    public String getStudentCollegeId() {
        return studentCollegeId;
    }

    public void setStudentCollegeId(String studentCollegeId) {
        this.studentCollegeId = studentCollegeId;
    }

    private String studentMailId;
    private String studentContactNumber;
    private String studentCollegeId;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

}
