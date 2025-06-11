package com.pluralsight;

import java.sql.*;

public class ProductService {
    public static void displayAllProducts(String username, String password) {
        // SQL query to retrieve product data
        String sql = """
                SELECT productid, productname, unitprice, unitsinstock
                FROM products;
                """;

        try (
                // Connect to the database
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", username, password);
                // Prepare the SQL statement
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                // Execute the query and get results
                ResultSet resultSet = preparedStatement.executeQuery()) {

            // Print table headers
            System.out.printf("%-4s %-40s %15s %10s%n", "Id", "Product Name", "Price", "Stock");
            System.out.println("_________________________________________________________________________________");

            // Loop through the result set and print each product
            while (resultSet.next()) {
                int productId = resultSet.getInt("productid");
                String productName = resultSet.getString("productname");
                double unitPrice = resultSet.getDouble("unitprice");
                int unitsInStock = resultSet.getInt("unitsinstock");

                System.out.printf("%-4d %-50s %15.2f %10d%n", productId, productName, unitPrice, unitsInStock);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving products.");
            e.printStackTrace(); // Developer-friendly error
        }
    }
}
