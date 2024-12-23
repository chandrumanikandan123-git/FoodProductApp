package com.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	 private String jdbcURL = "jdbc:mysql://localhost:3306/bookshop";
	    private String dbUser = "root";
	    private String dbPassword = "password";

	    public User checkLogin(String email, String password) throws SQLException, ClassNotFoundException {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
	            String sql = "SELECT * FROM users WHERE email = ? and password = ?";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setString(1, email);
	                statement.setString(2, password);

	                try (ResultSet result = statement.executeQuery()) {
	                    User user = null;

	                    if (result.next()) {
	                        user = new User();
	                        user.setId(result.getInt("id"));
	                        user.setFullname(result.getString("fullname"));
	                        user.setEmail(result.getString("email"));
	                        user.setPassword(result.getString("password"));
	                    }

	                    return user;
	                }
	            }
	        }
	    }
}
