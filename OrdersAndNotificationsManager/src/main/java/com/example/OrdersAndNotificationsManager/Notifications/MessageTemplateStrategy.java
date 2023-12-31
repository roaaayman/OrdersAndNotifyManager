// MessageTemplateStrategy.java
package com.example.OrdersAndNotificationsManager.Notifications;

import java.util.List;

public interface MessageTemplateStrategy {
    String createMessage(String customerName, List<String> purchasedItems);
}
