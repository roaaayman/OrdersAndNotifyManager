package com.example.OrdersAndNotificationsManager.Orders;

import java.util.List;

public interface Order {
    public String placeorder(List<String> ProductName);
    public String cancelorder();
    public String cancelShipping();
}
