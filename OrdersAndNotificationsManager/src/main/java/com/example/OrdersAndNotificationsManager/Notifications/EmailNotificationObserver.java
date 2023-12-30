package com.example.OrdersAndNotificationsManager.Notifications;

import org.springframework.stereotype.Service;

@Service
public class EmailNotificationObserver implements NotificationObserver {
    @Override
    public void update(String notification) {
        sendEmail(notification);
    }

    private void sendEmail(String notification) {
        // Simulating sending an email notification
        System.out.println("Sending email notification: " + notification);
    }
}

