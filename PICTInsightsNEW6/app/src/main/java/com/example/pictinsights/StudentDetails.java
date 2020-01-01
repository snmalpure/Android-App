package com.example.pictinsights;

public class StudentDetails {

    private String name;
    private String email;
    private String year;
    private String cell;
    private int count=1;

    public StudentDetails() {
        //
    }

    public int getCount() {
        return count++;
    }

    public String getStudentName() {

        return name;
    }

    public void setStudentName(String name) {

        this.name = name;
    }

    public String getStudentEmail() {

        return email;
    }

    public void setStudentEmail(String email) {

        this.email = email;
    }

    public String getStudentYear() {

        return year;
    }

    public void setStudentYear(String year) {

        this.year = year;
    }

    public String getStudentCell() {

        return cell;
    }

    public void setStudentCell(String cell) {

        this.cell = cell;
    }
}
