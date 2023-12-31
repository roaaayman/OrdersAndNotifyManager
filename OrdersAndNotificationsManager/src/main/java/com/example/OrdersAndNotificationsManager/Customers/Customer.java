package com.example.OrdersAndNotificationsManager.Customers;

import com.example.OrdersAndNotificationsManager.Orders.CompoundOrder;
import com.example.OrdersAndNotificationsManager.Orders.SimpleOrder;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String email;
    private String password;
    private double balance;
    private String location;
    private String address;

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    private String phonenum;
    private List<SimpleOrder> simpleOrders = new ArrayList<>();
    private List<CompoundOrder> compoundOrders = new ArrayList<>();


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Customer(String email, String password,  String location,String address,String phonenum) {
        this.password=password;
        this.email=email;
        this.location=location;
        this.address=address;
        this.phonenum=phonenum;
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

    public List<SimpleOrder> getSimpleOrders() {
        return simpleOrders;
    }

    public List<CompoundOrder> getCompoundOrders() {
        return compoundOrders;
    }

    public void addSimpleOrder(SimpleOrder order) {
        simpleOrders.add(order);
    }

    public void addCompoundOrder(CompoundOrder order) {
        compoundOrders.add(order);
    }

    public void removeSimpleOrder(SimpleOrder order) {
        simpleOrders.remove(order);
    }

    public void removeCompoundOrder(CompoundOrder order) {
        compoundOrders.remove(order);
    }

    public SimpleOrder getSimpleOrderById(int orderId) {
        for (SimpleOrder order : simpleOrders) {
            if (order.getOrderID()==orderId) {
                return order;
            }
        }
        return null;
    }

}