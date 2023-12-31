package com.example.OrdersAndNotificationsManager.MessageTemplate;

import com.example.OrdersAndNotificationsManager.MessageTemplate.MessageTemplateStrategy;
import com.example.OrdersAndNotificationsManager.Orders.SimpleOrder;

public class ShipmentMessageTemplate implements MessageTemplateStrategy {
    @Override
    public String createMessage(String customerName, SimpleOrder order) {
        String messageTemplate = "Dear {x}, your shipment of the items {y} is on its way. Thank you for shopping with us!";
        String personalizedMessage = messageTemplate.replace("{x}", customerName)
                .replace("{y}", String.join(",", order.getProductName()));

        return personalizedMessage;
    }
}
