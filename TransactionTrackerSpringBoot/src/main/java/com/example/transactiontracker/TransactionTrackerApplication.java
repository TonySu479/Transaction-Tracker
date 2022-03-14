package com.example.transactiontracker;

import com.example.transactiontracker.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionTrackerApplication {

    private final UserService userService;
    private static UserService userServiceStatic;

    public TransactionTrackerApplication(UserService userService) {
        this.userService = userService;
        userServiceStatic = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TransactionTrackerApplication.class, args);
        userServiceStatic.createInitialUsers();
    }

}
