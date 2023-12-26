package com.example.OrdersAndNotificationsManager.Customers;

import com.example.OrdersAndNotificationsManager.Customers.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private List<Customer> customerList = new ArrayList<>();

    // Method to create a new customer account
    public void createAccount(Customer c) {
        customerList.add(c);
    }


    // Method to check if the user exists and return user info if exists
    public Customer getCustomerByEmail(String email) {
        for (Customer customer : customerList) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }

    // Method to update the balance of a customer
    public Customer updateBalance(String email, double balance) {
        // Retrieve the customer from the list (simulating database retrieval)
        Customer customer = getCustomerByEmail(email);

        if (customer != null) {
            customer.setBalance(balance+customer.getBalance());
            return customer;
        } else {
            // Return null or throw an exception based on your error handling strategy
            return null;
        }
    }

    public boolean checkemailUniqueness(String email) {
        for (Customer customer : customerList) {
            if (customer.getEmail() .equals( email)) {
                return false;
            }
        }
        return true;

    }

}
