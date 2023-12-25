package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.Customers.Customer;
import com.example.OrdersAndNotificationsManager.Products.Products;
import com.example.OrdersAndNotificationsManager.Products.Products;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Creating a customer
        Customer customer = new Customer("john@example.com", "password123", "New York"); // email, password, location
        // Creating products
        List<Products> productList = new ArrayList<>();
        productList.add(new Products(1, "Product A", "Vendor A", "Category A", 10.0, 5, 100)); // serialNumber, name, vendor, category, price, countparts, quantity
        productList.add(new Products(2, "Product B", "Vendor B", "Category B", 15.0, 7, 50));
        // Add more products as needed
        // Add more products as needed

        // Creating SimpleOrder instance
        SimpleOrder simpleOrder = new SimpleOrder(customer, productList);

        // Simulating user input to place an order
        simpleOrder.placeorder();
    }
}
