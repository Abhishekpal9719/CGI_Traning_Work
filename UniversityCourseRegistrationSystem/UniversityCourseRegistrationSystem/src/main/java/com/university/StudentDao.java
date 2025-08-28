package com.university;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    int addStudent(Student s);
    List<Student> getAllStudents();
    Optional<Student> getById(int id);
}
