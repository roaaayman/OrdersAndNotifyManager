package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.Customers.Customer;
import com.example.OrdersAndNotificationsManager.Customers.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;

    @Autowired
    public OrderController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    // API endpoint to place a simple order
    @PostMapping("/simple")
    public String placeSimpleOrder(@RequestParam String email, @RequestParam List<String> productNames) {
        // Check if the customer exists
        Customer customer = customerService.getCustomerByEmail(email);
        if (customer == null) {
            return "email not available";
        }

        // Create a simple order
        SimpleOrder simpleOrder = new SimpleOrder(customer);
       // simpleOrder.setShippingFee(shippingFee);

        // Place the order

        return orderService.placeOrder(simpleOrder, productNames);
    }


    // API endpoint to place a compound order
    @PostMapping("/compound")
    public String placeCompoundOrder(
            @RequestParam String customerEmail,
            @RequestParam List<String> friendEmails,
            @RequestParam List<String> productNames
    ) {
        // Check if the customer exists
        Customer customer = customerService.getCustomerByEmail(customerEmail);
        if (customer == null) {
            return "Customer email not available";
        }

        // Create a compound order
        CompoundOrder compoundOrder = new CompoundOrder();

        // Create and add a simple order for the customer
        SimpleOrder customerOrder = new SimpleOrder(customer);
        compoundOrder.addSimpleOrder(customerOrder);

        // Add simple orders for friends
        for (String friendEmail : friendEmails) {
            // Check if the friend customer exists
            Customer friendCustomer = customerService.getCustomerByEmail(friendEmail);
            if (friendCustomer == null) {
                return "Friend email not available: " + friendEmail;
            }

            // Check if the friend's location matches the customer's location (you may need a method for this)
            if (!friendCustomer.getLocation().equals(customer.getLocation())) {
                return "Friend " + friendEmail + " has a different location than the customer";
            }

            // Create and add a simple order for the friend
            SimpleOrder friendOrder = new SimpleOrder(friendCustomer);
            compoundOrder.addSimpleOrder(friendOrder);
        }

        // Set shipping fee for the compound order (you may implement logic to calculate shipping fee)

        // Place the compound order
        return orderService.placeOrder(compoundOrder, productNames);
    }

}

