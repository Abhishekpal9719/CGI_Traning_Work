package com.university;

import java.sql.*;

public class DatabaseUtil {
    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            java.util.Properties props = new java.util.Properties();
            try (java.io.InputStream in = DatabaseUtil.class.getResourceAsStream("/application.properties")) {
                props.load(in);
            }
            url = props.getProperty("jdbc.url");
            user = props.getProperty("jdbc.user");
            password = props.getProperty("jdbc.password");
            Class.forName(props.getProperty("jdbc.driver"));
            initSchema();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize DB", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Create tables if they don't exist
    private static void initSchema() throws SQLException {
        try (Connection conn = getConnection();
             Statement st = conn.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS students (" +
                    "student_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "year INT NOT NULL)");
            st.execute("CREATE TABLE IF NOT EXISTS courses (" +
                    "course_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "title VARCHAR(255) NOT NULL, " +
                    "credits INT NOT NULL)");
            st.execute("CREATE TABLE IF NOT EXISTS registrations (" +
                    "student_id INT NOT NULL, " +
                    "course_id INT NOT NULL, " +
                    "PRIMARY KEY(student_id, course_id), " +
                    "FOREIGN KEY(student_id) REFERENCES students(student_id) ON DELETE CASCADE, " +
                    "FOREIGN KEY(course_id) REFERENCES courses(course_id) ON DELETE CASCADE)");
        }
    }
}
