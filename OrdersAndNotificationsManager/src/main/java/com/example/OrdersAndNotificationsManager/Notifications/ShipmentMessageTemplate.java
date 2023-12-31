package com.example.OrdersAndNotificationsManager.Notifications;

import java.util.List;

public class ShipmentMessageTemplate implements MessageTemplateStrategy {
    @Override
    public String createMessage(String customerName, List<String> shippedItems) {
        String messageTemplate = "Dear {x}, your shipment of the items {y} is on its way. Thank you for shopping with us!";
        String personalizedMessage = messageTemplate.replace("{x}", customerName)
                .replace("{y}", String.join(",", shippedItems));

        return personalizedMessage;
    }
}
