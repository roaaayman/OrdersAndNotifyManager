package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.Orders.Order;
import com.example.OrdersAndNotificationsManager.Products.Products;

import java.util.List;

public class SimpleOrder implements Order {
    private String orderId;
    private String customerId;
    private List<Products> products;
    private boolean shipped;
    private double shippingFees;

    // Constructor
    public SimpleOrder(String orderId, String customerId, List<Products> products) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.products = products;
        this.shipped = false;
        this.shippingFees = 0.0;
    }

    @Override
    public void placeorder() {

    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Products product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public void setShippingFees(double fees) {
        this.shippingFees = fees;
    }

    public void markAsShipped() {
        this.shipped = true;
    }

}
