package com.example.OrdersAndNotificationsManager.Notifications;

import com.example.OrdersAndNotificationsManager.Notifications.NotificationObserver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SMSNotificationObserver implements NotificationObserver {
    private List<String> smsNotifications = new ArrayList<>();

    @Override
    public void update(String notification) {
        sendSMS(notification);
        addNotification("Sending SMS: " + notification); // Add the notification
    }

    private void sendSMS(String notification) {
        // Simulating sending an SMS - Replace with actual SMS logic
        System.out.println("Sending SMS: " + notification);
    }

    private void addNotification(String message) {
        smsNotifications.add(message);
    }

    public List<String> getNotifications() {
        return smsNotifications;
    }
}
