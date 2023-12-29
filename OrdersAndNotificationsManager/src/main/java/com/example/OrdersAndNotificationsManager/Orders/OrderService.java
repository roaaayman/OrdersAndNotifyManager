package com.example.OrdersAndNotificationsManager.Orders;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    public String placeOrder(Order order, List<String> productNames) {

        // Pass the list of product names to the order
        String result = order.placeorder(productNames);
        return result;
    }
    public List<String> listOrdersForCustomer(String customerEmail) {
        List<String> orders = new ArrayList<>();
        List<String> simpleOrders = listSimpleOrdersForCustomer(customerEmail);
        orders.addAll(simpleOrders);
        List<String> compoundOrders = listCompoundOrdersForCustomer(customerEmail);
        orders.addAll(compoundOrders);
         return orders;
    }
    public List<String> listSimpleOrdersForCustomer(String customerEmail) {
        List<String> simpleOrders = new ArrayList<>();
        return simpleOrders;
    }
}
