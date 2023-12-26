package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.Customers.Customer;
import com.example.OrdersAndNotificationsManager.Orders.SimpleOrder;

import java.util.ArrayList;
import java.util.List;

class CompoundOrder implements Order {
    private List<SimpleOrder> orders; // Store multiple simple orders in a compound order
    private double shippingFee;

    private List<Customer> customers;

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
        List<List<String>> friendsProducts = new ArrayList<>();
        friendsProducts.add(ProductName.subList(2, friendsProducts.size()));

        // Create a simple order for the main customer
        SimpleOrder mainCustomerOrder = new SimpleOrder(mainCustomer);
        String mainCustomerOrderResult = mainCustomerOrder.placeorder(mainCustomerProducts);

        if (!mainCustomerOrderResult.equals("simple order placed")) {
            return mainCustomerOrderResult; // Return an error message if placing the main customer's order fails
        }
        orders.add(mainCustomerOrder);

        // Create simple orders for friends and add them to the compound order
        for (int i = 0; i < friends.size(); i++) {
            Customer friend = friends.get(i);
            List<String> friendProducts = friendsProducts.get(i);

            SimpleOrder friendOrder = new SimpleOrder(friend);
            String friendOrderResult = friendOrder.placeorder(friendProducts);

            // Check the result of placing the friend's order
            if (!friendOrderResult.equals("simple order placed")) {
                return friendOrderResult; // Return an error message if placing the friend's order fails
            }
            orders.add(friendOrder);
        }

        return "Compound order placed";
    }

    public double calculateTotal() {
        double total = 0.0;
        for (SimpleOrder order : orders) {
            total += order.calculateTotal();
        }
        return total;
    }
}
