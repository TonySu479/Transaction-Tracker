package com.example.transactiontracker.services.datainitializingservice;

import com.example.transactiontracker.services.productservice.ProductService;
import com.example.transactiontracker.services.userservice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class DataInitializingService {

    private final UserService userService;
    private final ProductService productService;

    @PostConstruct
    public void init() {
        userService.createInitialUsers();
        productService.createInitialProducts();
    }
}
