package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.Customers.Customer;
import com.example.OrdersAndNotificationsManager.Notifications.Notification;
import com.example.OrdersAndNotificationsManager.Notifications.NotificationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final NotificationService notificationService;

    public OrderService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void placeOrder(Order order, List<String> productNames, Customer customer) {
        // Pass the list of product names to the order
        order.placeorder(productNames);

        // Assuming the order placement is always successful
        String templateId = "order_placement_template"; // Template ID for order placement
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("{x}", customer.getEmail()); // Replace with recipient's name or email
        placeholders.put("{y}", String.join(", ", productNames)); // Join the product names

        Notification notification = new Notification(customer.getEmail(), templateId, placeholders);
        notificationService.sendNotification(notification); // Send the notification
    }
}
