package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.Customers.Customer;

import java.util.ArrayList;
import java.util.List;

public class CompoundOrder implements Order {
    private List<SimpleOrder> orders = new ArrayList<>();


    public void addSimpleOrder(SimpleOrder order) {
        orders.add(order);
    }


    @Override
    public String placeorder(List<String> ProductName) {
        for(SimpleOrder order:orders)
        {
            String result=order.placeorder(ProductName);
            if(!result.equals("simple order placed"))
            {
                return result;
            }
        }
        return "compound order placed";
    }
    public String getOrderDetails() {
        String orderDetails = "Compound Order of main customer and friends Orders:";

        for (SimpleOrder order : orders) {
            orderDetails += order.getOrderDetails() + ",";
        }

        return orderDetails;
    }
}
