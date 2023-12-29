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

    public void addTemplate(String templateId, String templateText) {
        templates.put(templateId, templateText);
    }

    public void sendNotification(Notification notification) {
        String template = templates.get(notification.getTemplateId());
        if (template != null) {
            String message = notification.generateMessage(template);
            // Logic to send the message through various channels (email, SMS, etc.)
            notifyObservers(notification); // Notify observers with the prepared notification
        } else {
            System.out.println("Template not found for the provided templateId");
        }
    }
}
