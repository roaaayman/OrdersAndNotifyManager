package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.Customers.Customer;
import com.example.OrdersAndNotificationsManager.Orders.SimpleOrder;

import java.util.ArrayList;
import java.util.List;

class CompoundOrder implements Order {
    private List<SimpleOrder> orders;
    private List<Customer> customers;
    private double shippingFee;

    public CompoundOrder(List<Customer> customers) {
        this.customers = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.shippingFee = 0.0;
    }

    @Override
    public String placeorder(List<String> ProductName)
    {
        // Split the customers list to determine main customer friends
        Customer mainCustomer = customers.get(0);
        List<Customer> friends = customers.subList(1, customers.size());

        for (Customer customer : customers) {
            if (!customer.getLocation().equals(mainCustomer.getLocation())) {
                return "Customers must have the same location as the main customer";
            }
        }

        // Split the productName list to determine products for the main customer and friends
        List<String> mainCustomerProducts = ProductName.subList(0, 2);

        // Loop through the product names and assign to each friend directly
        int friendIndex = 0;
        for (int i = 2; i < ProductName.size(); i += 2) {
            Customer friend = friends.get(friendIndex);
            List<String> friendProducts = ProductName.subList(i, i + 2);

            SimpleOrder friendOrder = new SimpleOrder(friend);
            String friendOrderResult = friendOrder.placeorder(friendProducts);

            // Check the result of placing the friend's order
            if (!friendOrderResult.equals("simple order placed")) {
                return friendOrderResult; // Return an error message if placing the friend's order fails
            }
            orders.add(friendOrder);
            friendIndex++;
        }

        // Create a simple order for the main customer
        SimpleOrder mainCustomerOrder = new SimpleOrder(mainCustomer);
        String mainCustomerOrderResult = mainCustomerOrder.placeorder(mainCustomerProducts);

        if (!mainCustomerOrderResult.equals("simple order placed")) {
            return mainCustomerOrderResult; // Return an error message if placing the main customer's order fails
        }
        orders.add(mainCustomerOrder);

        List<Double> OrderAmount = calculateOrder(mainCustomer, friends);

        // Deduct balance from each account
        for (int i = 0; i < orders.size(); i++) {
            Customer c;
            if (i == orders.size() - 1) {
                c = mainCustomer;
            } else {
                c = friends.get(i);
            }
            double orderCost = OrderAmount.get(i);
            c.setBalance(c.getBalance() - orderCost); // Deduct from each customer's balance
        }

        return "Compound order placed";
    }

    // Calculate Order's cost for each customer individually
    private List<Double> calculateOrder(Customer mainCustomer, List<Customer> friends) {
        List<Double> individualOrderCost = new ArrayList<>();
        for (SimpleOrder order : orders) {
            double orderCost = order.calculateTotal(); // Calculate the cost of each order
            individualOrderCost.add(orderCost);
        }
        return individualOrderCost;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (SimpleOrder order : orders) {
            total += order.calculateTotal();
        }
        return total;
    }
}
