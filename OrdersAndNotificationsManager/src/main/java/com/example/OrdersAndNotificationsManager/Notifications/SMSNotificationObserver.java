package com.example.OrdersAndNotificationsManager.Notifications;

import org.springframework.stereotype.Service;

@Service
public class SMSNotificationObserver implements NotificationObserver {
    @Override
    public void update(String notification) {
        sendSMS(notification);
    }

    private void sendSMS(String notification) {
        // Simulating sending an SMS - Replace this with actual SMS sending logic using an SMS API
        System.out.println("Sending SMS: " + notification);
        System.out.println("SMS Sent Successfully!");
    }
}
