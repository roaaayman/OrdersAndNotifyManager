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

    public SimpleOrder(Customer customer, List<Products> products) {
        this.customer = customer;
        this.products = products;
        this.shippingFee = 0.0;
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
                // Retrieve Product object based on name from the products list or inventory
                Products product = findProductByName(productName);
                if (product != null) {
                    System.out.print("Enter quantity for " + productName + ": ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    if (quantity > 0 && quantity <= product.getAvailableQuantity()) {
                        product.setQuantity(quantity);
                        products.add(product);
                    } else {
                        System.out.println("Invalid quantity or not enough stock!");
                    }
                } else {
                    System.out.println("Product not found!");
                }
            }
        }
        double total = calculateTotal();
        if (customer.getBalance() >= total) {
            customer.setBalance(customer.getBalance() - total);
            System.out.println("Simple order placed!");
        } else {
            System.out.println("Insufficient balance to place the order!");
        }
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

    // Method to find a product by name from the products list or inventory
    private Products findProductByName(String name) {
        for (Products product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null; // Product not found
    }

}