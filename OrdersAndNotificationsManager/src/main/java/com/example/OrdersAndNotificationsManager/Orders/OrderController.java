package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.Customers.Customer;
import com.example.OrdersAndNotificationsManager.Customers.CustomerService;
import com.example.OrdersAndNotificationsManager.Notifications.EmailLNotificationObserver;
import com.example.OrdersAndNotificationsManager.Notifications.SMSNotificationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final EmailLNotificationObserver emailObserver ;
    private final SMSNotificationObserver smsObserver ;


    @Autowired
    public OrderController(OrderService orderService, CustomerService customerService, SMSNotificationObserver smsObserver, EmailLNotificationObserver emailObserver) {
        this.orderService = orderService;
        this.customerService = customerService;
       // Register NotificationService as an observer
        this.smsObserver = smsObserver;
        this.emailObserver = emailObserver;
    }

    // API endpoint to place a simple order
    @PostMapping("/simple")
    public String placeSimpleOrder(@RequestParam String email, @RequestParam List<String> productNames) {
        // Check if the customer exists
        Customer customer = customerService.getCustomerByEmail(email);
        if (customer == null) {
            return "email not available";
        }
        SimpleOrder simpleOrder = new SimpleOrder(customer);

        simpleOrder.attachObservers(smsObserver, emailObserver);
        customer.addSimpleOrder(simpleOrder);
        String result= orderService.placeOrder(simpleOrder, productNames);

        return result;
    }

// API endpoint to place a compound order
    @PostMapping("/compound")
    public List<String> placeCompoundOrder(
            @RequestParam String customerEmail,
            @RequestParam List<String> friendEmails,
            @RequestParam List<String> customerProductNames,
            @RequestParam List<List<String>> friendProductNames
    ) {
        List<String> results = new ArrayList<>();
        // Check if the main customer exists
        Customer mainCustomer = customerService.getCustomerByEmail(customerEmail);
        if (mainCustomer == null) {
            return Collections.singletonList("main customer not available");
        }

        int numberOfCustomers= friendEmails.size()+1;
        // Create a compound order
        CompoundOrder compoundOrder = new CompoundOrder();
        boolean allFriendsAvailable = true;

        // Add simple orders for friends with their specified product names
        for (int i = 0; i < friendEmails.size(); i++) {
            String friendEmail = friendEmails.get(i);


            // Check if the friend customer exists
            Customer friendCustomer = customerService.getCustomerByEmail(friendEmail);
            if (friendCustomer == null )
            {
                results.add("Friend email not available : " + friendEmail);
                allFriendsAvailable=false;
                break;
            }

            // Check if the friend's location matches the main customer's location
             if (!friendCustomer.getLocation().equals(mainCustomer.getLocation())) {
                results.add("Friend " + friendEmail + " has a different location than the customer");
                allFriendsAvailable=false;
                break;
            }


        }

        if(allFriendsAvailable) {

            for(int i=0;i<friendEmails.size();i++)
            {
                List<String> friendProductList = friendProductNames.get(i);
                String friendEmail = friendEmails.get(i);
                Customer friendCustomer = customerService.getCustomerByEmail(friendEmail);
                SimpleOrder friendOrder=new SimpleOrder(friendCustomer);
                String friendResult = orderService.placeOrder(friendOrder, friendProductList);
                friendCustomer.addSimpleOrder(friendOrder);
                compoundOrder.addSimpleOrder(friendOrder);
                results.add("Friend " + friendEmail + ": " + friendResult);
            }


            SimpleOrder mainCustomerOrder = new SimpleOrder(mainCustomer);
            String mainCustomerResult = orderService.placeOrder(mainCustomerOrder, customerProductNames);
            compoundOrder.addSimpleOrder(mainCustomerOrder);
            results.add("Main Customer: " + mainCustomerResult);

            List<String> finalProducts = new ArrayList<>();

            for (int i = 0; i < friendEmails.size(); i++) {
                List<String> friendProductList = friendProductNames.get(i);
                finalProducts.addAll(friendProductList);
            }

            finalProducts.addAll(customerProductNames);



            String compoundOrderResult = orderService.placeOrder(compoundOrder, finalProducts);
            results.add("Compound Order: " + compoundOrderResult);

            mainCustomer.addCompoundOrder(compoundOrder);

        }
        return results;
    }
    @GetMapping("/getorder/{email}")
    public List<String> getOrdersForCustomer(@PathVariable String email) {
        Customer customer = customerService.getCustomerByEmail(email);
        if (customer == null) {
            return Collections.singletonList("Customer not found");
        }

        List<String> orders = new ArrayList<>();
        List<SimpleOrder> simpleOrders = customer.getSimpleOrders();
        for (SimpleOrder simpleOrder : simpleOrders) {
            orders.add( simpleOrder.getOrderDetails());
        }
        List<CompoundOrder> compoundOrders = customer.getCompoundOrders();
        for (CompoundOrder compoundOrder : compoundOrders) {
            orders.add("Compound Order: " + compoundOrder.getOrderDetails());
        }

        return orders;
    }
}