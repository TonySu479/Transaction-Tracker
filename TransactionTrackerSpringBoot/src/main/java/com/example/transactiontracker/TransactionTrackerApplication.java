package com.example.transactiontracker;

import com.example.transactiontracker.services.productservice.ProductService;
import com.example.transactiontracker.services.userservice.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionTrackerApplication.class, args);
    }

}
