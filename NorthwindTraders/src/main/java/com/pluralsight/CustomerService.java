package com.pluralsight;

import java.sql.*;

public class CustomerService {
    public static void displayAllCustomers(String username, String password) {
        // SQL query to retrieve customer data
        String sql = """
                SELECT customerid, companyname, contactname, country
                FROM customers;
                """;

        try (
                // Connect to the database
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", username, password);
                // Prepare the SQL statement
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                // Execute the query and get results
                ResultSet resultSet = preparedStatement.executeQuery()) {

            // Print table headers
            System.out.printf("%-10s %-40s %-25s %-15s%n", "Customer ID", "Company Name", "Contact Name", "Country");
            System.out.println("------------------------------------------------------------------------------------------");

            // Loop through the result set and print each customer
            while (resultSet.next()) {
                String customerId = resultSet.getString("customerid");
                String companyName = resultSet.getString("companyname");
                String contactName = resultSet.getString("contactname");
                String country = resultSet.getString("country");

                System.out.printf("%-10s %-40s %-25s %-15s%n", customerId, companyName, contactName, country);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customers.");
            e.printStackTrace(); // Developer-friendly error
        }
    }
}
