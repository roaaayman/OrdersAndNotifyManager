package com.example.OrdersAndNotificationsManager.Notifications;

import java.util.ArrayList;
import java.util.List;

public class NotificationService implements NotificationSubject {
    private List<NotificationObserver> observers = new ArrayList<>();

    @Override
    public void attach(NotificationObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(NotificationObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Notification notification) {
        for (NotificationObserver observer : observers) {
            observer.update(notification);
        }
    }

    public void sendNotification(Notification notification) {
        // Send notification logic
        notifyObservers(notification);
    }
}
