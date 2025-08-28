package com.university;

import java.util.List;

public interface RegistrationDao {
    void register(int studentId, int courseId);
    List<Registration> getAllRegistrations();
    // For JOIN views used by Service
    List<EnrollmentRecord> getStudentCourseJoin();
}
