package com.example.OrdersAndNotificationsManager.Notifications;

import com.example.OrdersAndNotificationsManager.Notifications.SMSNotificationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final SMSNotificationObserver smsNotificationObserver;
    private final EmailNotificationObserver emailNotificationObserver;

    @Autowired
    public NotificationController(
            SMSNotificationObserver smsNotificationObserver,
            EmailNotificationObserver emailNotificationObserver) {
        this.smsNotificationObserver = smsNotificationObserver;
        this.emailNotificationObserver = emailNotificationObserver;
    }

    @PostMapping("/trigger-sms-notification")
    public String triggerSMSNotification(@RequestBody String message) {
        smsNotificationObserver.update(message);
        return "SMS Notification Triggered!";
    }


}
