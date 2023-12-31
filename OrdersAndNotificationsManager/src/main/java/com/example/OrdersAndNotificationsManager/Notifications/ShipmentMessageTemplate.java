package com.example.OrdersAndNotificationsManager.Notifications;

import com.example.OrdersAndNotificationsManager.Orders.SimpleOrder;

import java.util.List;

public class ShipmentMessageTemplate implements MessageTemplateStrategy {
    @Override
    public String createMessage(String customerName, SimpleOrder order) {
        String messageTemplate = "Dear {x}, your shipment of the items {y} is on its way. Thank you for shopping with us!";
        String personalizedMessage = messageTemplate.replace("{x}", customerName)
                .replace("{y}", String.join(",", order.getProductName()));

        return personalizedMessage;
    }
}
