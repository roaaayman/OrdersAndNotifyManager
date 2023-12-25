package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.Orders.Order;

public class SimpleOrder implements Order {
    private String productName;

    public SimpleOrder(String productName) {
        this.productName = productName;
    }

    @Override
    public void placeorder() {

    }
}
