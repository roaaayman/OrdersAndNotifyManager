package com.example.OrdersAndNotificationsManager.Notifications;

import java.util.List;

public interface NotificationObserver {
    void update(String notification);
    List<String> getNotifications();

}
