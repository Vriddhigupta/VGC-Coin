package com.example.loginclient;

import android.graphics.Bitmap;
import android.net.Uri;

public class AppRequest {

    private String studentApplicationName;
    private String studentApplicationDescription;
    private String studentApplicationDate;
    private String studentApplicationOrganizer;
    private String studentApplicationCategory;
    private Uri file;

    public String getStudentApplicationName() {
        return studentApplicationName;
    }

    public void setStudentApplicationName(String studentApplicationName) {
        this.studentApplicationName = studentApplicationName;
    }

    public String getStudentApplicationDescription() {
        return studentApplicationDescription;
    }

    public void setStudentApplicationDescription(String studentApplicationDescription) {
        this.studentApplicationDescription = studentApplicationDescription;
    }

    public String getStudentApplicationDate() {
        return studentApplicationDate;
    }

    public void setStudentApplicationDate(String studentApplicationDate) {
        this.studentApplicationDate = studentApplicationDate;
    }

    public String getStudentApplicationOrganizer() {
        return studentApplicationOrganizer;
    }

    public void setStudentApplicationOrganizer(String studentApplicationOrganizer) {
        this.studentApplicationOrganizer = studentApplicationOrganizer;
    }

    public String getStudentApplicationCategory() {
        return studentApplicationCategory;
    }

    public void setStudentApplicationCategory(String studentApplicationCategory) {
        this.studentApplicationCategory = studentApplicationCategory;
    }

    public Uri getFile() {
        return file;
    }

    public void setFile(Uri file) {
        this.file = file;
    }
}
