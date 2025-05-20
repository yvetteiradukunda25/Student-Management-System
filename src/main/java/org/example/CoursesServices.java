package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursesServices implements StudentsInterface<courses, Integer> {
    private static final String URL = "jdbc:postgresql://localhost:5432/Student Management System";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "coding";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @Override
    public List<courses> findAll() {
        List<courses> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";
        try (Connection conn = connect(); Statement statement = conn.createStatement(); ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                courses.add(new courses(
                        result.getString(1),
                        result.getString(2)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return List.of();

    }

    @Override
    public courses findById(Integer id) {
        String query = "SELECT FROM courses WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                return new courses(
                        result.getString(1),
                        result.getString(2)
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public void deleteById(Integer id) {
        String query = "DELETE FROM courses WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("course deleted successfully");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void create(courses courses) {
        String query = "INSERT INTO courses (course_name, course_description ) VALUES (?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, courses.getCourse_name());
            pstmt.setString(2, courses.getCourse_description());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("course added successfully");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(courses courses, Integer id) {
        String query = "UPDATE courses SET course_name =?, course_description =?, id = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, courses.getCourse_name());
            pstmt.setString(2, courses.getCourse_description());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Course is well  updated");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}