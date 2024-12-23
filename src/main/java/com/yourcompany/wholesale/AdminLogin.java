package com.yourcompany.wholesale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminLogin {

    private static final String URL = "jdbc:sqlite:C:\\SQLite\\foodapp.db";
    private static final String DRIVER = "org.sqlite.JDBC";

    private static Connection connection;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initialize();

        // Perform login
        boolean loggedIn = login();

        if (loggedIn) {
            System.out.println("Login successful. You can now perform administrative tasks.");
            // Add your logic for administrative tasks here

            // For example, you can call your main menu
            // MainMenu.main(args);
        } else {
            System.out.println("Login failed. Exiting...");
        }

        // Close the scanner and the database connection
        scanner.close();
        closeConnection();
    }

    private static void initialize() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL);
            System.out.println("DB Connection working fine...");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean login() {
        System.out.println("Admin Login");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // Check if the provided credentials exist in the "users" table
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM users WHERE username = ? AND password = ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // If there is a match, the user is logged in
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
