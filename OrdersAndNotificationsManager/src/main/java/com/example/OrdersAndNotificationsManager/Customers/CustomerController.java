package com.example.OrdersAndNotificationsManager.Customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public Customer createAccount(@RequestParam String email, @RequestParam String password, @RequestParam double balance, @RequestParam String Location) {
        Customer existingCustomer = customerService.checkCustomer(email, password);

        if (existingCustomer != null) {
            // Handle the case where the email is already registered (e.g., return an error response)
            System.out.println("Email already registered");
            return null;
        }

        // Create a new customer account
        return customerService.createCustomer(email, password, balance,Location);
    }

    @GetMapping("/check")
    public Customer checkUser(@RequestParam String email, @RequestParam String password) {
        // Logic to check if the user exists and return user info if exists
        return customerService.checkCustomer(email, password);
    }

    @PutMapping("/{email}/balance")
    public Customer updateBalance(@PathVariable String email, @RequestParam double balance) {
        // Logic to update the balance of a customer
        return customerService.updateBalance(email, balance);
    }
}
