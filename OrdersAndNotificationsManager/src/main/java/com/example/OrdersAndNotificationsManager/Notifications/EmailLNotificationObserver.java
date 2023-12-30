package com.example.OrdersAndNotificationsManager.Notifications;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailLNotificationObserver implements NotificationObserver {
    private List<String> EmailNotification = new ArrayList<>();

    @Override
    public void update(String notification) {
        sendEmail(notification);
        addNotification("Sending email: " + notification); // Add the notification
    }

    private void sendEmail(String notification) {
        // Simulating sending an email - Replace with actual email logic
        System.out.println("Sending email: " + notification);
    }

    private void addNotification(String message) {
        EmailNotification.add(message);
    }

    public List<String> getNotifications() {
        return EmailNotification;
    }
}
