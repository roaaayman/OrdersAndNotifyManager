package com.example.OrdersAndNotificationsManager.Products;

public class Products {
    private int serialNumber;
    private String name;
    private String vendor;
    private String category;
    private double price;
    private int countparts;

    private int quantity;

    private int availableQuantity;

    // Constructor
    public Products(int serialNumber, String name, String vendor, String category, double price,int countparts , int availableQuantity) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.vendor = vendor;
        this.category = category;
        this.price = price;
        this.countparts=countparts;
        this.availableQuantity=availableQuantity;
    }


    // Setters
    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    // Getters
    public int getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getVendor() {
        return vendor;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getAvailableQuantity() {
        return availableQuantity;
    }
}
