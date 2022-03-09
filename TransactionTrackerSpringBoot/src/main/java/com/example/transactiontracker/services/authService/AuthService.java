package com.example.transactiontracker.services.authservice;

import com.example.transactiontracker.payload.dto.LoginRequest;
import com.example.transactiontracker.payload.dto.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthService {
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest);
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest);
}
