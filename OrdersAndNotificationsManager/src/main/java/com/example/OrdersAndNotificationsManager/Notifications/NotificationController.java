package com.example.OrdersAndNotificationsManager.Notifications;

import com.example.OrdersAndNotificationsManager.Notifications.SMSNotificationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final SMSNotificationObserver smsNotificationObserver;

    @Autowired
    public NotificationController(SMSNotificationObserver smsNotificationObserver) {
        this.smsNotificationObserver = smsNotificationObserver;
    }

    @GetMapping("/trigger-sms-notification")
    public String triggerSMSNotification() {
        // Simulate a message that you want to send as an SMS
        String message = "This is a sample SMS message.";

        // Call the update method of the observer with the message
        smsNotificationObserver.update(message);

        return "SMS Notification Triggered!";
    }
    @GetMapping("/trigger-email-notification")
    public String triggerEmailNotification()
    {
        // Simulate a message that you want to send as an SMS
        String message = "This is a sample Email message.";

        // Call the update method of the observer with the message
        smsNotificationObserver.update(message);

        return "SMS Notification Triggered!";

    }
}
