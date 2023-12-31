
package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.Customers.Customer;

import java.util.ArrayList;
import java.util.List;

public class CompoundOrder implements Order {
    private List<SimpleOrder> orders = new ArrayList<>();
    private OrderStatus status;
    private double totalShippingFee;
    private CompoundOrder compoundOrder;
    private boolean iscancelled;



    public void setTotalShippingFee(double totalShippingFee) {
        this.totalShippingFee = totalShippingFee;
    }
    public enum OrderStatus {
        PLACED,
        CONFIRMED,
        SHIPPED,
        CANCELLED
    }

    public void addSimpleOrder(SimpleOrder order) {
        orders.add(order);
    }

    public OrderStatus getStatus() {
        return status;
    }

    @Override
    public String placeorder(List<String> ProductName) {
        boolean allConfirmed = false;

        for (SimpleOrder order : orders) {
            String result = order.placeorder(ProductName);
            if (!result.equals("simple order placed")) {
                allConfirmed = true;

                break;
            }
        }

        if (allConfirmed) {
            int totalOrders = orders.size();
            if (totalOrders > 0) {
                double individualShippingFee = totalShippingFee / totalOrders;
                for (SimpleOrder order : orders) {
                    order.setShippingFee(50/orders.size());
                }
            }
          // Calculate shipping fee after all orders are confirmed
            status = OrderStatus.CONFIRMED;
            return "compound order confirmed";


        } else {

            return "some orders could not be placed";
        }
    }

    @Override
    public String cancelorder() {
        return null;
    }

    @Override
    public String cancelShipping() {
        return null;
    }



    public String getOrderDetails()
    {
        String orderDetails = "CompoundOrder :";
        for (SimpleOrder order : orders)
        {
            orderDetails += order.getOrderDetails() + " , ";
        }
        return orderDetails;
    }

}