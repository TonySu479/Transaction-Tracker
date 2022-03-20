package com.example.transactiontracker;

import com.example.transactiontracker.services.productservice.ProductService;
import com.example.transactiontracker.services.userService.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionTrackerApplication {

    private final UserService userService;
    private static UserService userServiceStatic;
    private final ProductService productService;
    private static ProductService productServiceStatic;

    public TransactionTrackerApplication(UserService userService, ProductService productService) {
        this.userService = userService;
        userServiceStatic = userService;
        this.productService = productService;
        productServiceStatic = productService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TransactionTrackerApplication.class, args);
        userServiceStatic.createInitialUsers();
        productServiceStatic.creatInitialProducts();
    }

}
