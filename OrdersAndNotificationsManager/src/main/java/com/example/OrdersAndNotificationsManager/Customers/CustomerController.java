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
    public String createAccount(@RequestBody Customer customer) {
        if(customerService.checkemailUniqueness(customer.getEmail()))
        {
            customerService.createAccount(customer);
            return "account created successfully";
        }
        else if(!customerService.checkemailUniqueness(customer.getEmail())){
            return "email already registered";
        }

        return null;
    }



    @GetMapping("/check/{email}")
    public Customer checkUser(@PathVariable("email") String email) {
       return customerService.getCustomerByEmail(email);
    }

    @PutMapping("/{email}/balance")
    public String updateBalance(@PathVariable String email, @RequestBody double balance) {
        // Logic to update the balance of a customer
         customerService.updateBalance(email, balance);
         return "Balance updated to "+balance;
    }
}
