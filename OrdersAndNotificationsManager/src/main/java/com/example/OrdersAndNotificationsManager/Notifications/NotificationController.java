package com.example.OrdersAndNotificationsManager.Notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final SMSNotificationObserver smsNotificationObserver;
    private final EmailLNotificationObserver emailNotificationObserver;

    @Autowired
    public NotificationController(SMSNotificationObserver smsNotificationObserver, EmailLNotificationObserver emailNotificationObserver) {
        this.smsNotificationObserver = smsNotificationObserver;
        this.emailNotificationObserver = emailNotificationObserver;
    }

    @GetMapping("/sms-notifications")
    public List<String> getSMSNotifications() {
        // Retrieve SMS notifications from the SMS observer
        return smsNotificationObserver.getNotifications();
    }

    @GetMapping("/email-notifications")
    public List<String> getEmailNotifications() {
        // Retrieve email notifications from the email observer
        return emailNotificationObserver.getNotifications();
    }
}
