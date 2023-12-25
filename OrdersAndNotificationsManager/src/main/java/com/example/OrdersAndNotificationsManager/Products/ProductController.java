package com.example.OrdersAndNotificationsManager.Products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{category}")
    public String countPartsByCategory(@PathVariable ("category") String category) {
        return productService.countProductsByCategory(category);
    }
}
