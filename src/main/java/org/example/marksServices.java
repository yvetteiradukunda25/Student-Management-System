package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class marksServices implements StudentsInterface<marks, Integer> {
    private static final String URL = "jdbc:postgresql://localhost:5432/Student Management System";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "coding";

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @Override
    public List<marks> findAll() {
        List<marks> marks = new ArrayList<>();
        String query = "SELECT * FROM marks";
        try (Connection conn = connect(); Statement statement = conn.createStatement(); ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                marks.add(new marks(
                        result.getInt(1),
                        result.getInt(2),
                        result.getInt(3)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return marks;

    }

    @Override
    public marks findById(Integer id) {
        String query = "SELECT * FROM marks WHERE student_id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                return new marks(
                        result.getInt(1),
                        result.getInt(2),
                        result.getInt(3)
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public void deleteById(Integer id) {
        String query = "DELETE FROM marks WHERE student_id = ? ";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("marks deleted successfully");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void create(marks marks) {
        String query = "INSERT INTO marks (student_id, course_id, marks) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, marks.getStudent_id());
            pstmt.setInt(2, marks.getCourse_id());
            pstmt.setInt(3, marks.getMarks());

            pstmt.executeUpdate();

            System.out.println("course added successfully");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void update(marks mark) {
        String query = "UPDATE marks SET course_id =?, marks =?, student_id = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, mark.getCourse_id());
            pstmt.setInt(2,mark.getStudent_id());
            pstmt.setInt(3, mark.getMarks());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Marks are well  updated");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}