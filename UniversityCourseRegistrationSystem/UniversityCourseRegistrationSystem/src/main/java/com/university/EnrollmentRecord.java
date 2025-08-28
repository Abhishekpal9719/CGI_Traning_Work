package com.university;

// A simple pair of Student and Course rows returned by a JOIN.
// Course can be null (when student has no registrations).
public class EnrollmentRecord {
    private final Student student;
    private final Course course; // nullable

    public EnrollmentRecord(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
}
