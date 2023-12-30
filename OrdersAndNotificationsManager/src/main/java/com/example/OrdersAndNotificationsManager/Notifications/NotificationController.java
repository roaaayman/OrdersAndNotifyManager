package com.example.OrdersAndNotificationsManager.Notifications;

import com.example.OrdersAndNotificationsManager.Customers.Customer;
import com.example.OrdersAndNotificationsManager.Customers.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final SMSNotificationObserver smsNotificationObserver;
    private final EmailLNotificationObserver emailNotificationObserver;

    @Autowired
    CustomerService customerService;
    public NotificationController(SMSNotificationObserver smsNotificationObserver, EmailLNotificationObserver emailNotificationObserver) {
        this.smsNotificationObserver = smsNotificationObserver;
        this.emailNotificationObserver = emailNotificationObserver;
    }

    @GetMapping("/sms-notifications/{phonenum}")
    public List<String> getSMSNotifications(@PathVariable String phonenum) {

        List<String> allSMSNotifications = smsNotificationObserver.getNotifications();
        List<String> cutomerSMSnotifications = new ArrayList<>();
        Customer customer=customerService.getCustomerByPhone(phonenum);

        if(customer!=null) {
            for (String notification : allSMSNotifications) {
                if (notification.contains(customer.getEmail())) {
                    cutomerSMSnotifications.add(notification);
                }
            }
        }

        return cutomerSMSnotifications;

    }

    @GetMapping("/email-notifications/{email}")
    public List<String> getEmailNotificationsForCustomer(@PathVariable String email) {

        List<String> allEmailNotifications = emailNotificationObserver.getNotifications();
        List<String> customerEmailNotifications = new ArrayList<>();

        Customer customer=customerService.getCustomerByEmail(email);
        if(customer!=null) {
            for (String notification : allEmailNotifications) {
                if (notification.contains(email)) {
                    customerEmailNotifications.add(notification);
                }
            }
        }

        return customerEmailNotifications;
    }

}
