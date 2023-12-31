// ConfirmationMessageTemplate.java
package com.example.OrdersAndNotificationsManager.Notifications;

import com.example.OrdersAndNotificationsManager.Orders.SimpleOrder;

import java.util.List;

public class ConfirmationMessageTemplate implements MessageTemplateStrategy {
    @Override
    public String createMessage(String customerName, SimpleOrder order) {
        String messageTemplate = "Dear {x}, your booking of the items {y} is confirmed. Thanks for using our store :)";
        String personalizedMessage = messageTemplate.replace("{x}", customerName)
                .replace("{y}", String.join(",", order.getProductName()));

        return personalizedMessage;
    }
}

