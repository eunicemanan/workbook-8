package com.pluralsight;

import com.pluralsight.data.CustomerDAO;
import com.pluralsight.data.ProductDAO;

import java.util.Scanner;

public class NorthwindApp {
    public static void main(String[] args) {

        boolean running = true;

        // colors
        String RESET = "\033[0m";
        String RED = "\033[31m";
        String GREEN = "\033[32m";

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
        while (running) {
            System.out.println("\n === Welcome to the Northwind Database! === ");
            System.out.println("[1] Display all products");
            System.out.println("[2] Display all customers");
            System.out.println("[3] Search product by product name");
            System.out.println("[0] Exit");
            System.out.println(" === Please select an option: ");
            choice = scanner.nextInt();

            //Call the appropriate service based on user input
            switch (choice) {
                case 1 -> {
                    var products = ProductDAO.getAllProducts(username, password);
                    System.out.printf("%-4s %-40s %25s %10s%n", "Id", "Product Name", "Price", "Stock");
                    System.out.println("______________________________________________________________________________________________________________");
                    for (var product : products) {
                        System.out.printf("%-4d %-50s %15.2f %10d%n",
                                product.getProductID(),
                                product.getProductName(),
                                product.getUnitPrice(),
                                product.getUnitsInStock());
                    }
                }
                case 2 -> CustomerDAO.displayAllCustomers(username, password);
                /*case 3 -> ProductDAO.searchProduct(String );*/
                case 0 -> {
                    System.out.println("Thank you for your time! Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


/*System.out.printf("%-4d %-50s %15.2f %10d%n", productId, productName, unitPrice, unitsInStock);*/
