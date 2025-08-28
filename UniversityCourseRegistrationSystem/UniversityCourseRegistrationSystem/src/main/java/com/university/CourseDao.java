package com.university;

import java.util.List;
import java.util.Optional;

public interface CourseDao {
    int addCourse(Course c);
    List<Course> getAllCourses();
    Optional<Course> getById(int id);
}
