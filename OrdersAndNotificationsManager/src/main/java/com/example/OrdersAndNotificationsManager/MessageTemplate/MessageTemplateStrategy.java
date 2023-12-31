// MessageTemplateStrategy.java
package com.example.OrdersAndNotificationsManager.MessageTemplate;

import com.example.OrdersAndNotificationsManager.Orders.SimpleOrder;

import java.util.List;

public interface MessageTemplateStrategy {
    String createMessage(String customerName, SimpleOrder order);
}
