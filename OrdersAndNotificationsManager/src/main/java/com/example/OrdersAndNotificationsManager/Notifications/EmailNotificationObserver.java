package com.example.OrdersAndNotificationsManager.Notifications;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailNotificationObserver implements NotificationObserver {
    private List<String> smsNotifications = new ArrayList<>();
    @Override
    public void update(String notification) {
        sendEmail(notification);
    }

    @Override
    public List<String> getNotifications() {
        return smsNotifications;
    }
    private void sendEmail(String notification) {
        // Simulating sending an email notification
        System.out.println("Sending email notification: " + notification);
    }

}

