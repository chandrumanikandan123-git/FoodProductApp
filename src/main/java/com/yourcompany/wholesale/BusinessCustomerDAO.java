// BusinessCustomerDAO.java
package com.yourcompany.wholesale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusinessCustomerDAO {
    private static final String URL = "jdbc:sqlite:C:\\SQLite\\foodapp.db";
    private static final String DRIVER = "org.sqlite.JDBC";

    // JDBC connection
    private Connection connection;

    public BusinessCustomerDAO() {
        try {
            // Load the SQLite JDBC driver
            Class.forName(DRIVER);

            // Establish the connection
          connection = DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Close the connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve all business customers from the database
    public List<BusinessCustomer> findAllBusinessCustomers() {
        List<BusinessCustomer> customers = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM businesscustomer")) {

            while (resultSet.next()) {
                BusinessCustomer customer = mapResultSetToBusinessCustomer(resultSet);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    // Find a business customer by its ID
    public BusinessCustomer findBusinessCustomer(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM businesscustomer WHERE id = ?")) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToBusinessCustomer(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Insert a new business customer into the database
    public boolean insertBusinessCustomer(BusinessCustomer customer) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO businesscustomer (businessName, addressLine1, addressLine2, addressLine3, country, postCode, telephoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, customer.getBusinessName());
            preparedStatement.setString(2, customer.getAddress().getAddressLine1());
            preparedStatement.setString(3, customer.getAddress().getAddressLine2());
            preparedStatement.setString(4, customer.getAddress().getAddressLine3());
            preparedStatement.setString(5, customer.getAddress().getCountry());
            preparedStatement.setString(6, customer.getAddress().getPostCode());
            preparedStatement.setString(7, customer.getTelephoneNumber());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing business customer in the database
    public boolean updateBusinessCustomer(BusinessCustomer customer) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE businesscustomer SET businessName=?, addressLine1=?, addressLine2=?, addressLine3=?, country=?, postCode=?, telephoneNumber=? WHERE id=?")) {

            preparedStatement.setString(1, customer.getBusinessName());
            preparedStatement.setString(2, customer.getAddress().getAddressLine1());
            preparedStatement.setString(3, customer.getAddress().getAddressLine2());
            preparedStatement.setString(4, customer.getAddress().getAddressLine3());
            preparedStatement.setString(5, customer.getAddress().getCountry());
            preparedStatement.setString(6, customer.getAddress().getPostCode());
            preparedStatement.setString(7, customer.getTelephoneNumber());
            preparedStatement.setInt(8, customer.getCustomerId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a business customer by its ID
    public boolean deleteBusinessCustomer(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM businesscustomer WHERE id=?")) {
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to map ResultSet to BusinessCustomer object
    private BusinessCustomer mapResultSetToBusinessCustomer(ResultSet resultSet) throws SQLException {
       String businessName = null;
		Address address = null;
		String telephoneNumber = null;
		// BusinessCustomer customer = new BusinessCustomer();
        BusinessCustomer customer = new BusinessCustomer(0, businessName, address, telephoneNumber);

        customer.setCustomerId(resultSet.getInt("id"));
        customer.setBusinessName(resultSet.getString("businessName"));

        Address address1 = new Address();
        address1.setAddressLine1(resultSet.getString("addressLine1"));
        address1.setAddressLine2(resultSet.getString("addressLine2"));
        address1.setAddressLine3(resultSet.getString("addressLine3"));
        address1.setCountry(resultSet.getString("country"));
        address1.setPostCode(resultSet.getString("postCode"));

        customer.setAddress(address1);
        customer.setTelephoneNumber(resultSet.getString("telephoneNumber"));

        return customer;
    }
}
