
package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.Customers.Customer;

import java.util.ArrayList;
import java.util.List;

public class CompoundOrder implements Order {
    private List<SimpleOrder> orders = new ArrayList<>();
    private OrderStatus status;

    private double shippingFee;


    public void setShippingFee(double fee) {
        this.shippingFee = fee;
    }

    public enum OrderStatus {
        PLACED,
        CONFIRMED,
        SHIPPED
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
                break; // Exit loop if any order is not confirmed
            }
        }

        if (allConfirmed) {
            calculateShippingFee(); // Calculate shipping fee after all orders are confirmed
            status = OrderStatus.CONFIRMED;
            return "compound order placed";

        } else {

            return "some orders could not be placed";
        }
    }

    private void calculateShippingFee() {
        int numberOfCustomers = orders.size();
        if (numberOfCustomers > 0) {
            double feePerCustomer = shippingFee / numberOfCustomers;

            for (SimpleOrder order : orders) {
                order.setShippingFee(feePerCustomer);
            }
        }
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