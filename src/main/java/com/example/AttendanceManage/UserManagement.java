package com.example.AttendanceManage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserManagement {
    public void doPost(String action, String name, String pass, String userId) throws IOException {
        if ("create".equals(action)) {
            createUser(name, pass);
        } else if ("delete".equals(action)) {
            deleteUser(userId);
        } else {
            System.out.println("Invalid action.");
        }
    }

    private void createUser(String name, String pass) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/your_database", "your_username", "your_password")) {
            String sql = "INSERT INTO usertable (name, pass) VALUES (?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, pass);
                statement.executeUpdate();
            }

            System.out.println("User created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error creating user.");
        }
    }

    private void deleteUser(String userId) {
        int userIdInt = Integer.parseInt(userId);

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/your_database", "your_username", "your_password")) {
            String sql = "DELETE FROM usertable WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, userIdInt);
                statement.executeUpdate();
            }

            System.out.println("User deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting user.");
        }
    }
}