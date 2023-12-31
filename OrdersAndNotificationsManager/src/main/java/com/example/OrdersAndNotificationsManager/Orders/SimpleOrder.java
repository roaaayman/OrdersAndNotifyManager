package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.Customers.Customer;
import com.example.OrdersAndNotificationsManager.MessageTemplate.CancellationMessageTemplate;
import com.example.OrdersAndNotificationsManager.MessageTemplate.ConfirmationMessageTemplate;
import com.example.OrdersAndNotificationsManager.MessageTemplate.ShipmentCancellationMessageTemplate;
import com.example.OrdersAndNotificationsManager.MessageTemplate.ShipmentMessageTemplate;
import com.example.OrdersAndNotificationsManager.Notifications.*;
import com.example.OrdersAndNotificationsManager.Products.DummyProductList;
import com.example.OrdersAndNotificationsManager.Products.Products;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SimpleOrder implements Order, NotificationSubject {
    private Customer customer;
    private List<Products> products;
    private double shippingFee;
    private List<NotificationObserver> observers;
    private OrderStatus status;
    private boolean iscancelled;
    private boolean isshippingcancelled;
    private static int increment=1;
    private  int orderID;

    private static Duration automatedDuration=Duration.ofSeconds(30);
    private LocalDateTime Time_of_placement;

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
        SHIPPED,
        CANCELLED,
        CANCELLEDSHIPPING
    }

    // Constructor
    public SimpleOrder(Customer customer) {
        this.customer = customer;
        this.products = new ArrayList<>();
        this.shippingFee = 50.0;
        this.status = OrderStatus.PLACED;
        this.observers = new ArrayList<>();
        this.orderID=increment++;
        this.Time_of_placement=LocalDateTime.now();
    }

    public int getOrderID() {
        return orderID;
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
            generateConfirmationMessage();

            if (status == OrderStatus.CONFIRMED) {
                String shippingConfirmationResult = confirmShipping();
                if (status == OrderStatus.SHIPPED) {
                    confirmationStatus = "---SHIPPED---";
                    generateshippingmessage();
                }
                return "---Confirmed---" +" Purchased products: " + String.join(",", addedproducts) +  ".  Total Deducted Amount: " + total +" Order id: "+orderID + " \n"+
                        confirmationStatus + shippingConfirmationResult;
            } else  {
                return "Order placed but confirmation failed";
            }

        } else {
            return "Not enough balance";
        }
    }
    public List<String> getProductName() {
        List<String> productNames = new ArrayList<>();
        for (Products product : products) {
            productNames.add(product.getName());
        }
        return productNames;
    }


    @Override
    public String cancelorder() {
        if(iscancelled)
        {
            return "order is already cancelled";
        }
        LocalDateTime currentTime=LocalDateTime.now();
        Duration timeSinceOrderPlacement = Duration.between(Time_of_placement, currentTime);
        if(timeSinceOrderPlacement.compareTo(automatedDuration)<=0) {
            iscancelled = true;
            customer.setBalance(customer.getBalance() + calculateTotal());
            customer.removeSimpleOrder(this);
            status = OrderStatus.CANCELLED;
            generateCancellationMessage();
            return "order is cancelled , check your refund";
        }
        else {
            return"you cannot cancel your order now";
        }
    }

    @Override
    public String cancelShipping() {
        if(isshippingcancelled)
        {
            return "shipping of that order is already cancelled";
        }
        LocalDateTime currentTime=LocalDateTime.now();
        Duration timeSinceOrderPlacement = Duration.between(Time_of_placement, currentTime);
        if(timeSinceOrderPlacement.compareTo(automatedDuration)<=0) {
            isshippingcancelled = true;
            customer.setBalance(customer.getBalance() + shippingFee);
            status = OrderStatus.CANCELLEDSHIPPING;
            generateShippmmentCancellationMessage();
            return "shipping is cancelled check your refund";
        }
        else {
            return "you cannot cancel your shipping now";
        }
    }

    // Method to confirm shipping
    public String confirmShipping() {
        if (status == OrderStatus.CONFIRMED) {
            double totalWithShipping = calculateTotal() + shippingFee;
            if (customer.getBalance() >= shippingFee) {
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

        // Create an instance of ConfirmationMessageTemplate
        ConfirmationMessageTemplate template = new ConfirmationMessageTemplate();

        // Use the instance method to create the message
        String message = template.createMessage(customer.getEmail(), this);

        // Now you can use the message wherever needed
        notifyObservers(message);

        return message;
    }
    public String generateCancellationMessage() {

        // Create an instance of ConfirmationMessageTemplate
        CancellationMessageTemplate template = new CancellationMessageTemplate();

        // Use the instance method to create the message
        String message = template.createMessage(customer.getEmail(), this);

        // Now you can use the message wherever needed
        notifyObservers(message);

        return message;
    }
    public String generateshippingmessage() {

        // Create an instance of ConfirmationMessageTemplate
        ShipmentMessageTemplate template = new ShipmentMessageTemplate();

        // Use the instance method to create the message
        String message = template.createMessage(customer.getEmail(), this);

        // Now you can use the message wherever needed
        notifyObservers(message);

        return message;
    }
    public String generateShippmmentCancellationMessage() {

        // Create an instance of ConfirmationMessageTemplate
        ShipmentCancellationMessageTemplate template = new ShipmentCancellationMessageTemplate();

        // Use the instance method to create the message
        String message = template.createMessage(customer.getEmail(), this);

        // Now you can use the message wherever needed
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