package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.Customers.Customer;
import com.example.OrdersAndNotificationsManager.Notifications.*;
import com.example.OrdersAndNotificationsManager.Products.DummyProductList;
import com.example.OrdersAndNotificationsManager.Products.Products;

import java.util.ArrayList;
import java.util.List;

public class SimpleOrder implements Order, NotificationSubject {
    private Customer customer;
    private List<Products> products;
    private double shippingFee;
    private List<NotificationObserver> observers;
    private OrderStatus status;
    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }
    public OrderStatus getStatus() {
        return status;
    }

    public enum OrderStatus {
        PLACED,
        CONFIRMED,
        SHIPPED
    }

    // Constructor
    public SimpleOrder(Customer customer) {
        this.customer = customer;
        this.products = new ArrayList<>();
        this.shippingFee = 50.0;
        this.status = OrderStatus.PLACED;
        this.observers = new ArrayList<>();
    }

    @Override
    public String placeorder(List<String> ProductName) {
        List<Products> productss = DummyProductList.getDummyProducts();
        List<String> addedproducts = new ArrayList<>();
        for (String productName : ProductName) {
            boolean productFound = false;
            for (Products p : productss) {
                if (p.getName().equals(productName)) {
                    products.add(p);
                    addedproducts.add(productName);
                    productFound = true;
                    break;
                }

            }
            if (!productFound) {
                return "Product not available:" + productName;
            }

        }
        double total = calculateTotal();
        if (customer.getBalance() >= total) {
            customer.setBalance(customer.getBalance() - total);
            status = OrderStatus.CONFIRMED;
            String confirmationStatus = "---CONFIRMED---";

            if (status == OrderStatus.CONFIRMED) {
                String shippingConfirmationResult = confirmShipping();
                if (status == OrderStatus.SHIPPED) {
                    confirmationStatus = "---SHIPPED---";
                }
                return "---Confirmed---" +" Purchased products: " + String.join(",", addedproducts) +  ".  Total Deducted Amount: " + total + " \n" +
                        confirmationStatus + shippingConfirmationResult;
            } else {
                return "Order placed but confirmation failed";
            }
        } else {
            return "Not enough balance";
        }
    }

    // Method to confirm shipping
    public String confirmShipping() {
        if (status == OrderStatus.CONFIRMED) {
            double totalWithShipping = calculateTotal() + shippingFee;
            if (customer.getBalance() >= totalWithShipping) {
                customer.setBalance(customer.getBalance() - shippingFee);
                status = OrderStatus.SHIPPED;
                return "Order has been shipped. Total Amount with Shipping: " + totalWithShipping;
            } else {
                return "Not enough balance to cover shipping fees";
            }
        } else {
            return "Order has not been confirmed yet";
        }
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Products product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public String getOrderDetails() {
        String orderDetails = "Simple Order: " + customer.getEmail() + ", Products:";

        for (int i = 0; i < products.size(); i++) {
            orderDetails += " Product: " + products.get(i).getName() + ", Price: " + products.get(i).getPrice() + " & ";
        }

        String totalamount_shipping="Total amount: "+calculateTotal()+" ,Shipping fee: "+shippingFee;
        return orderDetails+totalamount_shipping;
    }
    public String generateConfirmationMessage() {
        List<String> addedProducts = new ArrayList<>();
        for (Products product : products) {
            addedProducts.add(product.getName());
        }

        String message= MessageTemplate.generateConfirmationMessage(customer.getEmail(), addedProducts);
        notifyObservers(message);

        return message;
    }


    @Override
    public void attach(NotificationObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(NotificationObserver observer) {
        observers.remove(observer);
    }
    public void attachObservers( SMSNotificationObserver smsObserver, EmailLNotificationObserver emailObserver) {
        this.attach(smsObserver); // Attach SMS observer
        this.attach(emailObserver); // Attach Email observer
    }
    @Override
    public void notifyObservers(String notification) {
        for (NotificationObserver observer : observers) {
            observer.update(notification);
        }
    }


}
