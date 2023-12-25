package com.example.OrdersAndNotificationsManager.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // API endpoint to place a simple order
    @PostMapping("/simple")
    public ResponseEntity<String> placeSimpleOrder(@RequestParam String productName) {
        Order simpleOrder = new SimpleOrder(productName);
        orderService.placeOrder(simpleOrder);
        return ResponseEntity.ok("Simple order placed successfully.");
    }

    // API endpoint to place a compound order
    @PostMapping("/compound")
    public ResponseEntity<String> placeCompoundOrder(@RequestBody List<String> productNames) {
        CompoundOrder compoundOrder = new CompoundOrder();
        for (String productName : productNames) {
            Order simpleOrder = new SimpleOrder(productName);
            compoundOrder.addOrder(simpleOrder);
        }
        orderService.placeOrder(compoundOrder);
        return ResponseEntity.ok("Compound order placed successfully.");
    }
}

