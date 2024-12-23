package com.yourcompany.wholesale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationService {
    private Connection connection;

    public AuthenticationService(Connection connection) {
        this.connection = connection;
    }

    public boolean authenticate(String username, String password) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM users WHERE username = ? AND password = ?")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // If there's a matching user, authentication is successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Authentication failed due to an exception
        }
    }
}
