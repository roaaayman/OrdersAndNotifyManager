package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.Orders.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public void placeOrder(Order order) {
        // Perform common logic for placing any order, e.g., validation, logging, etc.
        System.out.println("Placing order...");

        // Delegate the order-specific logic to the order itself
        order.placeorder();

        // Additional logic after placing the order if needed
        System.out.println("Order placed successfully.");
    }
}
