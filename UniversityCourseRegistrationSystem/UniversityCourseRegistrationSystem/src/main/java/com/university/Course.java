package com.university;

import java.util.Objects;

public class Course {
    private Integer courseId;
    private String title;
    private int credits;

    public Course() {}

    public Course(Integer courseId, String title, int credits) {
        this.courseId = courseId;
        this.title = title;
        this.credits = credits;
    }

    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }

    @Override
    public String toString() {
        return String.format("Course{id=%d, title='%s', credits=%d}", courseId, title, credits);
    }
}
