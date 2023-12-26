package com.example.OrdersAndNotificationsManager.Products;

import java.util.Arrays;
import java.util.List;

public class DummyProductList {

    public static List<Products> getDummyProducts() {
        return Arrays.asList(
                new Products(1001, "Laptop", "ABC Electronics", "Electronics", 899.99),
                new Products(1002, "Smartphone", "XYZ Mobiles", "Electronics", 599.50),
                new Products(1003, "Men's Shoes", "Fashion Haven", "Fashion", 79.99),
                new Products(1004, "Dress", "Fashionista Boutique", "Fashion", 129.75),
                new Products(1005, "Book", "Book Emporium", "Books", 24.99),
                new Products(1006, "Coffee Maker", "Kitchen Essentials", "Home & Kitchen", 149.00)

        );
    }
}
