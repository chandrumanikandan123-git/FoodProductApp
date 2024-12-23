package com.yourcompany.wholesale;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FoodProductDAO {
    private static final String URL = "jdbc:sqlite:C:\\SQLite\\foodapp.db";
    private static final String DRIVER = "org.sqlite.JDBC";

    // JDBC connection
    private Connection connection;

    public FoodProductDAO() {
        try {
            // Load the SQLite JDBC driver
            Class.forName(DRIVER);

            // Establish the connection
            connection = DriverManager.getConnection(URL);
            System.out.println("DB Connection working fine...");
        } catch (ClassNotFoundException  e) {
            e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // Close the connection
    public void closeConnection() {
        try {
            if ( connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all products from the database
    public List<FoodProduct> findAllProducts() {
        List<FoodProduct> products = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from foodproduct")) {

            while (resultSet.next()) {
                FoodProduct product = mapResultSetToFoodProduct(resultSet);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    // Find a product by its ID
    public FoodProduct findProduct(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM foodproduct WHERE id = ?")) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToFoodProduct(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Insert a new product into the database
    public boolean insertProduct(FoodProduct product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO foodproduct ( id, SKU, description, category, price) VALUES ( ?, ?, ?, ?, ?)")) {
        	preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getSKU());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getCategory());
            preparedStatement.setInt(5, product.getPrice());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing product in the database
    public boolean updateProduct(FoodProduct product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE foodproduct SET id=?, SKU=?, description=?, category=?, price=? WHERE id=?")) {
        	
        	preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getSKU());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getCategory());
            preparedStatement.setInt(5, product.getPrice());
            //preparedStatement.setInt(5, product.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a product by its ID
    public boolean deleteProduct(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM foodproduct WHERE id=?")) {
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to map ResultSet to FoodProduct object
    private FoodProduct mapResultSetToFoodProduct(ResultSet resultSet) throws SQLException {
        FoodProduct product = new FoodProduct();
        product.setId(resultSet.getInt("id"));
        product.setSKU(resultSet.getString("SKU"));
        product.setDescription(resultSet.getString("description"));
        product.setCategory(resultSet.getString("category"));
        product.setPrice(resultSet.getInt("price"));
        return product;
    }
}
