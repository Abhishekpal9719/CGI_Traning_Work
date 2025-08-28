package com.university;

public class Registration {
    private int studentId;
    private int courseId;

    public Registration(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getStudentId() { return studentId; }
    public int getCourseId() { return courseId; }
}
