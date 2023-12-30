package com.example.OrdersAndNotificationsManager.Notifications;

import java.util.List;

public class MessageTemplate {
    public static String generateConfirmationMessage(String customerName, List<String> purchasedItems) {
        String messageTemplate = "Dear {x}, your booking of the items {y} is confirmed. Thanks for using our store :)";
        String personalizedMessage = messageTemplate.replace("{x}", customerName)
                .replace("{y}", String.join(",", purchasedItems));

        return personalizedMessage;
    }
}
