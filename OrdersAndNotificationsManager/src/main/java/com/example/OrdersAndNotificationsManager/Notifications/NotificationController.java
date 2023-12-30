package com.example.OrdersAndNotificationsManager.Notifications;

import com.example.OrdersAndNotificationsManager.Notifications.SMSNotificationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final SMSNotificationObserver smsNotificationObserver;

    @Autowired
    public NotificationController(SMSNotificationObserver smsNotificationObserver) {
        this.smsNotificationObserver = smsNotificationObserver;
    }

    @GetMapping("/sms-notifications")
    public List<String> getSMSNotifications() {
        // Retrieve SMS notifications from the SMS observer
        return smsNotificationObserver.getNotifications();
    }
}
