package com.pluralsight.data;

import com.pluralsight.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public static List<Product> getAllProducts(String username, String password) {
        // SQL query to retrieve product data
        String sql = """
                SELECT productid, productname, unitprice, unitsinstock
                FROM products;
                """;

        List<Product> products = new ArrayList<>();

        for (Product p : products) {
            System.out.println(p);
        }

        try (
                // Connect to the database
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", username, password);
                // Prepare the SQL statement
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                // Execute the query and get results
                ResultSet resultSet = preparedStatement.executeQuery()) {


            // Loop through the result set and print each product
            while (resultSet.next()) {
                int productId = resultSet.getInt("productid");
                String productName = resultSet.getString("productname");
                double unitPrice = resultSet.getDouble("unitprice");
                int unitsInStock = resultSet.getInt("unitsinstock");
                Product product = new Product(productId, productName, unitPrice, unitsInStock);
                products.add(product);

            }
        } catch (SQLException e) {
            System.out.println("Error retrieving products.");
            e.printStackTrace(); // Developer-friendly error

        }
        return products;

    }

    public static List<Product> searchProduct(String searchTerm, String username, String password) {
        String sql = """
                SELECT *
                FROM products
                WHERE productname LIKE ?;
                """;

        List<Product> products = new ArrayList<>();

        for (Product p : products) {
            System.out.println(p);

            try (
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", username, password);
                    PreparedStatement preparedStatement = connection.prepareStatement(sql)
            ) {
                // Add wildcards to allow partial matching (e.g., "%apple%")
                preparedStatement.setString(1, "%" + searchTerm + "%");

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int productID = resultSet.getInt("ProductID");
                        String productName = resultSet.getString("ProductName");
                        double unitPrice = resultSet.getDouble("UnitPrice");
                        int unitsInStock = resultSet.getInt("UnitsInStock");

                        Product product = new Product(productID, productName, unitPrice, unitsInStock);
                        products.add(product);
                    }
                }

            } catch (SQLException e) {
                System.out.println("There was an error retrieving the products. Please try again, or contact support.");
                e.printStackTrace(); // developer-friendly message
            }
        }

            return products;
        }
    }

