package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements StudentsInterface<Students, Integer>{
    private static final String URL = "jdbc:postgresql://localhost:5432/StudentManagementSystem";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "coding";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
}

    @Override
    public List<Students> findAll() {
        List<Students> students = new ArrayList<>();
        String query = "SELECT * FROM Students";
        try(Connection conn = connect(); Statement statement = conn.createStatement(); ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                students.add(new Students(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString( 4),
                        result.getString(5)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return List.of();

    }

    @Override
    public Students findById(Integer id) {
        return null;
    }



    @Override
    public void deleteById(Integer id) {
        String query = "DELETE FROM students WHERE id = ?";
        try(Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student deleted successfully");
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void create(Students students) {
        String query = "INSERT INTO students ( first_name, last_name, email, date_of_birth) VALUES (?, ?, ?, CAST (? AS DATE ))";
        try(Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(2, students.getFirst_name());
            pstmt.setString(3, students.getLast_name());
            pstmt.setString(4, students.getEmail());
            pstmt.setString(5, students.getDate_of_birth());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student added successfully");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Students students, Integer id) {
        String query = "UPDATE students SET first_name =?, last_name =?,  email =?, date_of_birth =?,  id = ?";

        try(Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, students.getFirst_name());
            pstmt.setString(2, students.getLast_name());
            pstmt.setString(3, students.getEmail());
            pstmt.setString(4, students.getDate_of_birth());
            pstmt.setInt(5, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Task updated successfully");
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
