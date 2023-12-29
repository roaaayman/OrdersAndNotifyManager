package com.example.OrdersAndNotificationsManager.Notifications;

import java.util.Map;

public class Notification {
    private String recipient;
    private String templateId;
    private Map<String, String> placeholders;

    // Constructor
    public Notification(String recipient, String templateId, Map<String, String> placeholders) {
        this.recipient = recipient;
        this.templateId = templateId;
        this.placeholders = placeholders;
    }

    // Getters and Setters
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Map<String, String> getPlaceholders() {
        return placeholders;
    }

    public void setPlaceholders(Map<String, String> placeholders) {
        this.placeholders = placeholders;
    }

    // Method to replace placeholders in the template
    public String generateMessage(String template) {
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            template = template.replace(entry.getKey(), entry.getValue());
        }
        return template;
    }
}
