package com.example.OrdersAndNotificationsManager.Orders;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    public String placeOrder(Order order, List<String> productNames) {

        // Pass the list of product names to the order
        String result = order.placeorder(productNames);
        return result;
    }
}
