package com.example.OrdersAndNotificationsManager.Notifications;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationService implements NotificationSubject {
    private List<NotificationObserver> observers = new ArrayList<>();
    private Map<String, String> templates = new HashMap<>();

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
    public void addTemplate(String templateId, String templateText) {
        templates.put(templateId, templateText);
    }

    public void sendNotification(Notification notification) {
        String template = templates.get(notification.getTemplateId());
        if (template != null) {
            String message = notification.generateMessage(template);
            notifyObservers(message); // Notify observers with the prepared message
        } else {
            System.out.println("Template not found for the provided templateId");
        }
    }

}
