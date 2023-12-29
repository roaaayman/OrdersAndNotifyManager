package com.example.OrdersAndNotificationsManager.Orders;

import java.util.ArrayList;
import java.util.List;

public class CompoundOrder implements Order {
    private List<SimpleOrder> orders = new ArrayList<>();

    private String location;

    public void setLocation(String location) {
        this.location = location;
    }

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
}
