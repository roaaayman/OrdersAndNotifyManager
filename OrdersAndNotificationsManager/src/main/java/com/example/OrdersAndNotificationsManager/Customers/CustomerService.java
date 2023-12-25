package com.example.OrdersAndNotificationsManager.Customers;

import com.example.OrdersAndNotificationsManager.Customers.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private List<Customer> customerList = new ArrayList<>();

    // Method to create a new customer account
    public Customer createCustomer(String email, String password, double balance,String Location) {

        for (Customer customer:customerList) {
            if(customer.getEmail()==email)
            {
                System.out.println("email already registered");
            }
        }

        Customer customer = new Customer(email, password, balance,Location);
        customerList.add(customer);

        return customer;
    }

    // Method to check if the user exists and return user info if exists
    public Customer checkCustomer(String email, String password) {
        for (Customer customer : customerList) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                return customer;
            }
        }

        // Return null or throw an exception based on your error handling strategy
        return null;
    }

    // Method to update the balance of a customer
    public Customer updateBalance(String email, double balance) {
        // Retrieve the customer from the list (simulating database retrieval)
        Customer customer = getCustomerByEmail(email);

        if (customer != null) {
            customer.setBalance(balance);
            return customer;
        } else {
            // Return null or throw an exception based on your error handling strategy
            return null;
        }
    }

    private Customer getCustomerByEmail(String email) {
        for (Customer customer : customerList) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }
}
