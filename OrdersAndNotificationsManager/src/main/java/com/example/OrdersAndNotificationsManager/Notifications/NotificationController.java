package com.example.OrdersAndNotificationsManager.Notifications;

import com.example.OrdersAndNotificationsManager.Customers.Customer;
import com.example.OrdersAndNotificationsManager.Customers.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private  SMSNotificationObserver smsNotificationObserver;
    private  EmailLNotificationObserver emailNotificationObserver;
    private  Queue<String> notificationsQueue = new LinkedList<>();


    @Autowired
    CustomerService customerService;
    public NotificationController(SMSNotificationObserver smsNotificationObserver, EmailLNotificationObserver emailNotificationObserver) {
        this.smsNotificationObserver = smsNotificationObserver;
        this.emailNotificationObserver = emailNotificationObserver;

    }

    @GetMapping("/smsNotify/{phonenum}")
    public List<String> getSMSNotifications(@PathVariable String phonenum) {

        List<String> allSMSNotifications = smsNotificationObserver.getNotifications();
        List<String> cutomerSMSnotifications = new ArrayList<>();
        Customer customer=customerService.getCustomerByPhone(phonenum);

        if(customer!=null) {
            while(!notificationsQueue.isEmpty())
            {
                String notification=notificationsQueue.poll();
                if(notification.contains(customer.getEmail()))
                {
                    cutomerSMSnotifications.add(notification);
                }
            }
            notificationsQueue.addAll(allSMSNotifications);
        }

        return cutomerSMSnotifications;

    }

    @GetMapping("/emailNotify/{email}")
    public List<String> getEmailNotifications(@PathVariable String email) {

        List<String> allEmailNotifications = emailNotificationObserver.getNotifications();
        List<String> customerEmailNotifications = new ArrayList<>();

        Customer customer=customerService.getCustomerByEmail(email);
        if(customer!=null) {
            if(customer!=null) {
                while(!notificationsQueue.isEmpty())
                {
                    String notification=notificationsQueue.poll();
                    if(notification.contains(customer.getEmail()))
                    {
                        customerEmailNotifications.add(notification);
                    }
                }
                notificationsQueue.addAll(allEmailNotifications);
            }

            return customerEmailNotifications;

        }

        return customerEmailNotifications;
    }



}