package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.Notifications.NotificationObserver;
import com.example.OrdersAndNotificationsManager.Notifications.NotificationSubject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService  {
    private List<NotificationObserver> observers = new ArrayList<>();

    public String placeOrder(Order order, List<String> productNames) {

        // Pass the list of product names to the order
        String result = order.placeorder(productNames);

        return result;



    }



}
