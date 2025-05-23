package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements StudentsInterface<Students, Integer> {
    private static final String URL = "jdbc:postgresql://localhost:5432/Student Management System";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "coding";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    @Override
    public void create(Students students) {
        String query = "INSERT INTO students ( first_name, last_name, email, date_of_birth) VALUES (?, ?, ?, CAST (? AS DATE ))";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, students.getFirst_name());
            pstmt.setString(2, students.getLast_name());
            pstmt.setString(3, students.getEmail());
            pstmt.setString(4, students.getDate_of_birth());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student added successfully");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    @Override
    public List<Students> findAll() {
        List<Students> students = new ArrayList<>();
        String query = "SELECT * FROM Students";
        try (Connection conn = connect(); Statement statement = conn.createStatement(); ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                students.add(new Students(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return students;

    }

    @Override
    public Students findById(Integer id) {
        String query = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                return new Students(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5)
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Students students) {
        String query = "UPDATE students SET first_name = ?, last_name = ?, email = ?, date_of_birth = ? WHERE id = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, students.getFirst_name());
            pstmt.setString(2, students.getLast_name());
            pstmt.setString(3, students.getEmail());
            pstmt.setDate(4, Date.valueOf(students.getDate_of_birth()));
            pstmt.setInt(5, students.getId());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("No student found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }


    @Override
    public void deleteById(Integer id) {
        String deleteMarks = "DELETE FROM marks WHERE student_id = ?";
        String deleteStudent = "DELETE FROM students WHERE id = ?";

        try (Connection conn = connect()) {
            conn.setAutoCommit(false); // start transaction

            // 1. delete marks
            try (PreparedStatement ps1 = conn.prepareStatement(deleteMarks)) {
                ps1.setInt(1, id);
                ps1.executeUpdate();
            }

            // 2. delete student
            try (PreparedStatement ps2 = conn.prepareStatement(deleteStudent)) {
                ps2.setInt(1, id);
                int rowsAffected = ps2.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Student deleted successfully.");
                } else {
                    System.out.println("No student found with that ID.");
                }
            }

            conn.commit(); // commit transaction
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }






}
