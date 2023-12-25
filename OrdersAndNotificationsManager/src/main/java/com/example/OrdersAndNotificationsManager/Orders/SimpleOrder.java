package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.Customers.Customer;
import com.example.OrdersAndNotificationsManager.Products.Products;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleOrder implements Order {
    private Customer customer;
    private List<Products> products;
    private double shippingFee;

    public SimpleOrder(Customer customer , List<Products> products) {
        this.customer = customer;
        this.products = new ArrayList<>();
        this.shippingFee = 0.0;
    }


    public void addProduct(Products product) {
        products.add(product);
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Products product : products) {
            total += product.getPrice() * product.getQuantity();
        }
        return total + shippingFee;
    }


    @Override
    public void placeorder() {
        Scanner scanner = new Scanner(System.in);
        boolean addingProducts = true;

        while (addingProducts) {
            System.out.print("Enter product name (or 'done' to finish): ");
            String productName = scanner.nextLine();

            if (productName.equals("done")) {
                addingProducts = false;
            } else {
                // Retrieve Products object based on name using the Products class method
                Products product = null;
                product.setName(productName);
                if (product != null) {
                    System.out.print("Enter quantity for " + productName + ": ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    products.add(product);
                    product.setQuantity(quantity);
                } else {
                    System.out.println("Product not found!");
                }
            }
        }
        double total = calculateTotal();
        customer.setBalance(customer.getBalance() - total);
        System.out.println("Simple order placed!");
    }
}
