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
    /*@PostMapping("/compound")
    public ResponseEntity<String> placeCompoundOrder(@RequestBody List<String> productNames) {
        CompoundOrder compoundOrder = new CompoundOrder();
        for (String productName : productNames) {
            Order simpleOrder = new SimpleOrder(productName);
            compoundOrder.addOrder(simpleOrder);
        }
        orderService.placeOrder(compoundOrder);
        return ResponseEntity.ok("Compound order placed successfully.");
    }*/
}

