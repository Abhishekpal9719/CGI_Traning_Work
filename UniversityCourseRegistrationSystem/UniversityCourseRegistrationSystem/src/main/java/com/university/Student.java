package com.university;

import java.util.Objects;

public class Student {
    private Integer studentId;
    private String name;
    private int year;

    public Student() {}

    public Student(Integer studentId, String name, int year) {
        this.studentId = studentId;
        this.name = name;
        this.year = year;
    }

    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public String toString() {
        return String.format("Student{id=%d, name='%s', year=%d}", studentId, name, year);
    }
}
