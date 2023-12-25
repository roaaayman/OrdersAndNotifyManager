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


}
