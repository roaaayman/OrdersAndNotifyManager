package com.example.OrdersAndNotificationsManager.Notifications;

public class NotificationService {
    private NotificationSubject subject;
    private SMSNotificationObserver smsObserver;
    private EmailNotificationObserver emailObserver;

    public void attachSMSAndEmailObservers() {
        subject.attach(smsObserver);
        subject.attach(emailObserver);
    }



}
