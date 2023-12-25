package com.example.OrdersAndNotificationsManager.Products;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service

public class ProductService {

    private List<Products> productList=DummyProductList.getDummyProducts();

    // Constructor to initialize the productList

    public List<Products> getAllProducts() {
        return productList;
    }

    public String countProductsByCategory(String categoryToCount) {
        int ElectronicsCount = 0;
        int FashionCount = 0;
        int Home_KitchenCount = 0;
        int BooksCount = 0;

        for (Products product : productList) {
            String productCategory = product.getCategory();
            if (productCategory.equals("Electronics")) {
                ElectronicsCount++;
            } else if (productCategory.equals("Fashion")) {
                FashionCount++;
            } else if (productCategory.equals("Books")) {
                BooksCount++;
            } else if (productCategory.equals("Home & Kitchen")) {
                Home_KitchenCount++;
            }
        }

        switch (categoryToCount) {
            case "Electronics":
                return "Count for Electronics: " + ElectronicsCount;
            case "Fashion":
                return "Count for Fashion: " + FashionCount;
            case "Books":
                return "Count for Books: " + BooksCount;
            case "Home & Kitchen":
                return "Count for Home & Kitchen: " + Home_KitchenCount;
            default:
                return "Invalid category";
        }
    }


}
