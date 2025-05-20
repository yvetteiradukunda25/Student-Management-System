package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class marksServices implements StudentsInterface<marks, Integer> {
    private static final String URL = "jdbc:postgresql://localhost:5432/Student Management System";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "coding";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @Override
    public List<marks> findAll() {
        List<marks> mark = new ArrayList<>();
        String query = "SELECT * FROM marks";
        try (Connection conn = connect(); Statement statement = conn.createStatement(); ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                mark.add(new marks(
                        result.getInt(1),
                        result.getInt(2),
                        result.getInt(3)

                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return mark;

    }

    @Override
    public marks findById(Integer id) {
        String query = "SELECT FROM marks WHERE student_id = ?";
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
    public void deleteById (Integer id){
        String query = "DELETE FROM marks WHERE student_id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student deleted successfully");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void create (marks mark) {
        String query = "INSERT INTO marks ( student_id, course_id, marks) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, marks.getStudent_id());
            pstmt.setInt(2, marks.getCourse_id());
            pstmt.setInt(3, marks.getMarks());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student added successfully");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    @Override
    public void update(Students students){
        String query = "UPDATE students SET first_name =?, last_name =?,  email =?, date_of_birth =?,  id = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, students.getId());
            pstmt.setString(2, students.getFirst_name());
            pstmt.setString(3, students.getLast_name());
            pstmt.setString(4, students.getEmail());
            pstmt.setString(5, students.getDate_of_birth());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("student updated successfully");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



}
