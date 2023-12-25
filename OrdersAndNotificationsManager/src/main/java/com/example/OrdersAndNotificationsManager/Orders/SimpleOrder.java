package com.example.OrdersAndNotificationsManager.Orders;

import com.example.OrdersAndNotificationsManager.Customers.Customer;
import com.example.OrdersAndNotificationsManager.Products.DummyProductList;
import com.example.OrdersAndNotificationsManager.Products.Products;

import java.util.ArrayList;
import java.util.List;

public class SimpleOrder implements Order {
    private Customer customer;
    private List<Products> products;
    private double shippingFee;


    // Constructor
    public SimpleOrder(Customer customer) {
        this.customer = customer;
        this.products = new ArrayList<>();
        this.shippingFee = 0.0;
    }

    @Override
    public String placeorder(List<String> ProductName) {
        List<Products> productss= DummyProductList.getDummyProducts();
        for (String productName: ProductName) {
            Products product=findProductByName(productss,productName);
            if(product!=null)
            {
                products.add(product);
            }

        }
        double total = calculateTotal();
        if(customer.getBalance()>=total)
        {
            customer.setBalance(customer.getBalance()-total);
            return "simple order placed";
        }
        else
        {
            return "no enough balance";
        }

    }
    public Products findProductByName(List<Products> products, String productName) {
        for (Products product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }



    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Products product : products) {
            total += product.getPrice();
        }
        return total;
    }

}
