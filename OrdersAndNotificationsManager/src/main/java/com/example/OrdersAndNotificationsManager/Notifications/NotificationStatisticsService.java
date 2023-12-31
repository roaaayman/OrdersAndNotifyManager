package com.example.OrdersAndNotificationsManager.Notifications;

import java.util.ArrayList;
import java.util.List;

public class NotificationStatisticsService {


    private List<String> notifiedEmails = new ArrayList<>();
    private List<String> notifiedPhoneNumbers = new ArrayList<>();
    private List<String> msgTemplates = new ArrayList<>();

    public void setMsgTemplates(List<String> msgTemplates) {
        this.msgTemplates = msgTemplates;
    }

    public void setNotifiedEmails(List<String> notifiedEmails) {
        this.notifiedEmails = notifiedEmails;
    }

    public void setNotifiedPhoneNumbers(List<String> notifiedPhoneNumbers) {
        this.notifiedPhoneNumbers = notifiedPhoneNumbers;
    }

    public void CountEmailNotifications(String email)
    {
        notifiedEmails.add(email);
    }
    public void CountSMSNotifications(String phonenum)
    {
        notifiedPhoneNumbers.add(phonenum);
    }
    public void CountTemplatess(String template)
    {
        msgTemplates.add(template);
    }
    public List<String> getMsgTemplates() {
        return msgTemplates;
    }

    public List<String> getNotifiedEmails() {
        return notifiedEmails;
    }

    public List<String> getNotifiedPhoneNumbers() {
        return notifiedPhoneNumbers;
    }




}
