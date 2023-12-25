package com.example.OrdersAndNotificationsManager.Customers;

public class Customer {

    private String email;
    private String password;
    private double balance;
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Customer(String email, String password,  String location) {
        this.password=password;
        this.email=email;
        this.location=location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}