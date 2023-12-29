package com.example.OrdersAndNotificationsManager.Notifications;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NotificationService implements NotificationSubject {
    private List<NotificationObserver> observers = new ArrayList<>();
    private Map<String, String> templates = new HashMap<>();
    private Queue<Notification> notificationQueue = new LinkedList<>();

    @Override
    public void attach(NotificationObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(NotificationObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String notification) {
        for (NotificationObserver observer : observers) {
            observer.update(notification);
        }
    }
    public void addNotificationToQueue(Notification notification) {
        notificationQueue.add(notification);
    }

    public List<Notification> getNotificationQueue() {
        return new ArrayList<>(notificationQueue);
    }

    public void sendNotificationsFromQueue() {
        while (!notificationQueue.isEmpty()) {
            Notification notification = notificationQueue.poll();
            // Process notification and send it to appropriate channels (email, SMS)
            processNotification(notification);
        }
    }

    private void processNotification(Notification notification) {
        // Logic to handle sending notifications through appropriate channels
        // For example:
        // If (notification.getRecipient().hasEmail()) -> send email
        // If (notification.getRecipient().hasPhoneNumber()) -> send SMS
        // Replace this logic with actual sending mechanisms (email/SMS APIs)
        // You can use EmailNotificationObserver and SMSNotificationObserver here
    }


}
