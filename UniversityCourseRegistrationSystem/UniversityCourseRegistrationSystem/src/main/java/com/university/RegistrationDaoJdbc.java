package com.university;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDaoJdbc implements RegistrationDao {
    @Override
    public void register(int studentId, int courseId) {
        String sql = "INSERT INTO registrations(student_id, course_id) VALUES(?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Registration> getAllRegistrations() {
        List<Registration> list = new ArrayList<>();
        String sql = "SELECT student_id, course_id FROM registrations";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Registration(rs.getInt(1), rs.getInt(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<EnrollmentRecord> getStudentCourseJoin() {
        List<EnrollmentRecord> list = new ArrayList<>();
        String sql = "SELECT s.student_id, s.name, s.year, c.course_id, c.title, c.credits " +
                "FROM students s " +
                "LEFT JOIN registrations r ON s.student_id = r.student_id " +
                "LEFT JOIN courses c ON r.course_id = c.course_id " +
                "ORDER BY s.student_id";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student s = new Student(rs.getInt(1), rs.getString(2), rs.getInt(3));
                Integer courseId = (Integer) rs.getObject(4);
                Course c = null;
                if (courseId != null) {
                    c = new Course(courseId, rs.getString(5), rs.getInt(6));
                }
                list.add(new EnrollmentRecord(s, c));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
