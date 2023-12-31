package com.example.OrdersAndNotificationsManager.MessageTemplate;

import com.example.OrdersAndNotificationsManager.Orders.SimpleOrder;

public class CancellationMessageTemplate implements MessageTemplateStrategy {
    @Override
    public String createMessage(String customerName, SimpleOrder order) {
        String messageTemplate = "Dear {x}, your cancellation of order with ID {y} is confirmed. Thanks for using our store :)";
        String personalizedMessage = messageTemplate.replace("{x}", customerName)
                .replace("{y}",String.valueOf(order.getOrderID()));

        return personalizedMessage;
    }
}
