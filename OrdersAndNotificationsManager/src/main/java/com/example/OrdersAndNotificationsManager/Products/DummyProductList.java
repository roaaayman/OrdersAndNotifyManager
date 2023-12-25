package com.example.OrdersAndNotificationsManager.Products;

import java.util.Arrays;
import java.util.List;

public class DummyProductList {

    public static List<Products> getDummyProducts() {
        return Arrays.asList(
                new Products(1001, "Laptop XYZ", "ABC Electronics", "Electronics", 899.99),
                new Products(1002, "Smartphone ABC", "XYZ Mobiles", "Electronics", 599.50),
                new Products(1003, "Men's Leather Shoes", "Fashion Haven", "Fashion", 79.99),
                new Products(1004, "Women's Floral Dress", "Fashionista Boutique", "Fashion", 129.75),
                new Products(1005, "Book: 'The Great Adventure'", "Book Emporium", "Books", 24.99),
                new Products(1006, "Coffee Maker Deluxe", "Kitchen Essentials", "Home & Kitchen", 149.00),
                new Products(1007, "Fitness Tracker Pro", "FitTech", "Health & Fitness", 79.50)

        );
    }
}
