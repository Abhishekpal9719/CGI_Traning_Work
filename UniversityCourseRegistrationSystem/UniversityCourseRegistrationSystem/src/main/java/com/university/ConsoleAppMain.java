package com.university;

import java.util.*;
import java.util.stream.Collectors;

public class ConsoleAppMain {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        StudentDao studentDao = new StudentDaoJdbc();
        CourseDao courseDao = new CourseDaoJdbc();
        RegistrationDao registrationDao = new RegistrationDaoJdbc();
        UniversityService service = new UniversityService(studentDao, courseDao, registrationDao);

        System.out.println("=== University Course Registration System ===");
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1:
                        String name = readLine("Student name: ");
                        int year = readInt("Year (e.g., 1..4): ");
                        int sid = service.addStudent(name, year);
                        System.out.println("Added Student with ID: " + sid);
                        break;
                    case 2:
                        String title = readLine("Course title: ");
                        int credits = readInt("Credits: ");
                        int cid = service.addCourse(title, credits);
                        System.out.println("Added Course with ID: " + cid);
                        break;
                    case 3:
                        int rsid = readInt("Student ID: ");
                        int rcid = readInt("Course ID: ");
                        service.registerStudentForCourse(rsid, rcid);
                        System.out.println("Registered Student " + rsid + " to Course " + rcid);
                        break;
                    case 4:
                        Map<Student, List<Course>> map = service.getAllStudentsWithCourses();
                        map.forEach((s, courses) -> {
                            String courseStr = courses.isEmpty() ? "(no courses)"
                                    : courses.stream().map(c -> c.getTitle() + " [" + c.getCredits() + "]")
                                    .collect(Collectors.joining(", "));
                            System.out.println(s + " -> " + courseStr);
                        });
                        break;
                    case 5:
                        int min = readInt("Minimum credits: ");
                        service.searchCoursesByMinCredits(min).forEach(System.out::println);
                        break;
                    case 6:
                        int courseId = readInt("Course ID: ");
                        service.getStudentsRegisteredInCourse(courseId).forEach(System.out::println);
                        break;
                    case 7:
                        service.sortStudentsByYearThenName().forEach(System.out::println);
                        break;
                    case 8:
                        service.calculateTotalCreditsPerStudent().forEach((s, total) ->
                                System.out.println(s + " -> Total Credits: " + total));
                        break;
                    case 9:
                        seedSampleData(service);
                        System.out.println("Seeded sample data.");
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Bye!");
    }

    private static void printMenu() {
        System.out.println("\n1. Add Student");
        System.out.println("2. Add Course");
        System.out.println("3. Register Student for Course");
        System.out.println("4. View All Students with Registered Courses");
        System.out.println("5. Search Courses by Minimum Credit Requirement");
        System.out.println("6. Get Students Registered in a Particular Course");
        System.out.println("7. Sort Students by Year and then by Name");
        System.out.println("8. Calculate Total Credits Per Student");
        System.out.println("9. Seed Sample Data");
        System.out.println("0. Exit");
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = scanner.nextLine();
                return Integer.parseInt(s.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static void seedSampleData(UniversityService service) {
        int s1 = service.addStudent("Alice", 1);
        int s2 = service.addStudent("Bob", 2);
        int s3 = service.addStudent("Charlie", 3);

        int c1 = service.addCourse("Data Structures", 4);
        int c2 = service.addCourse("Databases", 3);
        int c3 = service.addCourse("Operating Systems", 4);
        int c4 = service.addCourse("Networks", 3);

        service.registerStudentForCourse(s1, c1);
        service.registerStudentForCourse(s1, c2);
        service.registerStudentForCourse(s2, c3);
        service.registerStudentForCourse(s2, c4);
        // s3 no registrations
    }
}
