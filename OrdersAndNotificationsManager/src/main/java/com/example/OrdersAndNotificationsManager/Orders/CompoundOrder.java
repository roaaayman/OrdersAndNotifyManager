package com.example.OrdersAndNotificationsManager.Orders;

import java.util.ArrayList;
import java.util.List;

public class CompoundOrder implements Order {
    private List<Order> orders = new ArrayList<>();

    private String location;

    public void setLocation(String location) {
        this.location = location;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public String placeorder(List<String> ProductName) {
        System.out.println("Placing compound order to location:"+location);
        for (Order order : orders) {
            order.placeorder(ProductName);
        }

        return null;
    }
}
