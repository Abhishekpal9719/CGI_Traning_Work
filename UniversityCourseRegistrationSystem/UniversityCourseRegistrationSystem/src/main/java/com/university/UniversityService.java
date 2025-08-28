package com.university;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UniversityService {
    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final RegistrationDao registrationDao;

    public UniversityService(StudentDao studentDao, CourseDao courseDao, RegistrationDao registrationDao) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.registrationDao = registrationDao;
    }

    // CRUD proxies
    public int addStudent(String name, int year) {
        return studentDao.addStudent(new Student(null, name, year));
    }

    public int addCourse(String title, int credits) {
        return courseDao.addCourse(new Course(null, title, credits));
    }

    public void registerStudentForCourse(int studentId, int courseId) {
        // naive existence validation
        if (!studentDao.getById(studentId).isPresent()) {
            throw new IllegalArgumentException("Student " + studentId + " not found");
        }
        if (!courseDao.getById(courseId).isPresent()) {
            throw new IllegalArgumentException("Course " + courseId + " not found");
        }
        registrationDao.register(studentId, courseId);
    }

    // 4. View All Students with Registered Courses
    public Map<Student, List<Course>> getAllStudentsWithCourses() {
        List<EnrollmentRecord> join = registrationDao.getStudentCourseJoin();
        // group by Student (distinct via studentId equality), collect courses (ignoring null course)
        Map<Student, List<Course>> grouped = join.stream()
                .collect(Collectors.groupingBy(
                        EnrollmentRecord::getStudent,
                        LinkedHashMap::new,
                        Collectors.mapping(EnrollmentRecord::getCourse,
                                Collectors.filtering(Objects::nonNull, Collectors.toList()))
                ));
        // ensure students with no registrations are present too
        studentDao.getAllStudents().forEach(s -> grouped.putIfAbsent(s, new ArrayList<>()));
        return grouped;
    }

    // 5. Search Courses by Minimum Credit Requirement
    public List<Course> searchCoursesByMinCredits(int minCredits) {
        return courseDao.getAllCourses().stream()
                .filter(c -> c.getCredits() >= minCredits)
                .sorted(Comparator.comparing(Course::getCredits).thenComparing(Course::getTitle))
                .collect(Collectors.toList());
    }

    // 6. Get Students Registered in a Particular Course
    public List<Student> getStudentsRegisteredInCourse(int courseId) {
        List<EnrollmentRecord> join = registrationDao.getStudentCourseJoin();
        return join.stream()
                .filter(er -> er.getCourse() != null && er.getCourse().getCourseId() == courseId)
                .map(EnrollmentRecord::getStudent)
                .distinct()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }

    // 7. Sort Students by Year and then by Name
    public List<Student> sortStudentsByYearThenName() {
        return studentDao.getAllStudents().stream()
                .sorted(Comparator.comparingInt(Student::getYear).thenComparing(Student::getName))
                .collect(Collectors.toList());
    }

    // 8. Calculate Total Credits Per Student
    public Map<Student, Integer> calculateTotalCreditsPerStudent() {
        List<EnrollmentRecord> join = registrationDao.getStudentCourseJoin();
        Map<Student, Integer> totals = join.stream()
                .filter(er -> er.getCourse() != null)
                .collect(Collectors.groupingBy(
                        EnrollmentRecord::getStudent,
                        LinkedHashMap::new,
                        Collectors.summingInt(er -> er.getCourse().getCredits())
                ));
        // include students with 0 credits
        studentDao.getAllStudents().forEach(s -> totals.putIfAbsent(s, 0));
        return totals;
    }
}
