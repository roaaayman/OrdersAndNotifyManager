package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.Customers.Customer;
import com.example.OrdersAndNotificationsManager.Products.Products;

import java.util.ArrayList;
import java.util.List;

public class SimpleOrder implements Order {
    private Customer customer;
    private List<Products> products;
    private double shippingFee;


    // Constructor
    public SimpleOrder(Customer customer) {
        this.customer = customer;
        this.products = new ArrayList<>();
        this.shippingFee = 0.0;
    }

    @Override
    public void placeorder() {
        double total = calculateTotal();
        customer.setBalance(customer.getBalance() - total);
        System.out.println("Simple order placed!");
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
            total += product.getPrice();
        }
        return total + shippingFee;
    }

}
