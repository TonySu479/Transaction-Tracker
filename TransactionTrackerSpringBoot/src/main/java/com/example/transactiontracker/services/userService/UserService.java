package com.example.transactiontracker.services.userservice;

import com.example.transactiontracker.models.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    void save(User user);
}
