package com.example.OrdersAndNotificationsManager.Notifications;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class NotificationStatistics {
    private static Map<String, Integer> notifiedContacts = new HashMap<>();
    private static Map<String, Integer> sentTemplates = new HashMap<>();

    public static void trackSuccessfulNotification(String contact, String template) {
        notifiedContacts.put(contact, notifiedContacts.getOrDefault(contact, 0) + 1);
        sentTemplates.put(template, sentTemplates.getOrDefault(template, 0) + 1);
    }

    public Map.Entry<String, Integer> getMostNotifiedContact() {
        return Collections.max(notifiedContacts.entrySet(), Map.Entry.comparingByValue());
    }

    public Map.Entry<String, Integer> getMostSentTemplate() {
        return Collections.max(sentTemplates.entrySet(), Map.Entry.comparingByValue());
    }
}

