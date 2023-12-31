package com.example.OrdersAndNotificationsManager.Notifications;

public class NotificationStatistics {
    private static int totalNotifications = 0;
    private static int totalTemplatesSent = 0;

    private static String mostNotifiedCustomer = null;
    private static int countOfCustomerNotifications = 0;

    private static String mostSentTemplate = null;
    private static int mostSentTemplateCount = 0;

    public static void trackSuccessfulNotification(String contact, String template) {
        totalNotifications++;
        totalTemplatesSent++;

        int contactCount = 1;
        if (mostNotifiedCustomer != null && mostNotifiedCustomer.equals(contact)) {
            contactCount = countOfCustomerNotifications + 1;
        }

        int templateCount = 1;
        if (mostSentTemplate != null && mostSentTemplate.equals(template)) {
            templateCount = mostSentTemplateCount + 1;
        }

        if (contactCount > countOfCustomerNotifications) {
            mostNotifiedCustomer = contact;
            countOfCustomerNotifications = contactCount;
        }

        if (templateCount > mostSentTemplateCount) {
            mostSentTemplate = template;
            mostSentTemplateCount = templateCount;
        }
    }

    public String getMostNotifiedCustomer() {
        return mostNotifiedCustomer;
    }

    public int getMostNotifiedCustomerCount() {
        return countOfCustomerNotifications;
    }

    public String getMostSentTemplate() {
        return mostSentTemplate;
    }

    public int getMostSentTemplateCount() {
        return mostSentTemplateCount;
    }
}
