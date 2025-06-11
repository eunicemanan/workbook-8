package com.pluralsight;

import java.sql.*;
import java.util.Scanner;

public class NorthwindApp {
    public static void main(String[] args) throws SQLException {

        // colors
        String RESET = "\033[0m";
        String RED = "\033[31m";
        String GREEN = "\033[32m";
        String YELLOW = "\033[33m"; // Added for consistency

        // CHECK IF USERNAME AND PASSWORD ARE PROVIDED
        if (args.length < 2) {
            System.out.println(RED + "Please provide database username and password as arguments." + RESET);
            return;

        }
        String username = args[0];
        String password = args[1];

        Scanner scanner = new Scanner(System.in);
        int choice;

        // Display menu until use chooses to exit
        do {
            System.out.println("\n === Welcome to the Northwind Database! === ");
            System.out.println("[1] Display all products");
            System.out.println("[2] Display all customers");
            System.out.println("[0] Exit");
            System.out.println(" === Please select an option: ");
            choice = scanner.nextInt();

            //Call the appropriate service based on user input
            switch (choice) {
                case 1 -> ProductService.displayAllProducts(username, password);
                case 2 -> CustomerService.displayAllCustomers(username, password);
                case 0 -> System.out.println("Thanks you for your time! Goodbye!");
                default -> System.out.println("Invalid choice. Please try again");
            }

        } while (choice != 0);
        //create a SQL statement/query
       /* String sql = """
                 select
                     productid,
                     productname,
                     unitprice,
                     unitsinstock
                 from products;
                """;*/
    }
}

      /*  try (
                //create a connection
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind", username, password);
                //create a SQL statement
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                //execute the statement/query
                ResultSet resultSet = preparedStatement.executeQuery()){

            //print header row
            System.out.printf("%-4s %-40s %15s %10s%n", "Id", "Product Name", "Price", "Stock");
            System.out.println("_________________________________________________________________________________");

            //loop through the results and display them
            while (resultSet.next()) {
                int productId = resultSet.getInt("productid");
                String productName = resultSet.getString("productname");
                Double unitPrice = resultSet.getDouble("unitprice");
                int unitsInStock = resultSet.getInt("unitsinstock");

                //print row
                System.out.printf("%-4d %-40s %15.2f %10d%n", productId, productName, unitPrice, unitsInStock);
            }
        } catch (SQLException e) {
            //display user friendly error message
            System.out.println("There was an error retrieving the data. Please try again or contact support.");
            //display error message for the developer
            e.printStackTrace();
        } finally {
        }*/
