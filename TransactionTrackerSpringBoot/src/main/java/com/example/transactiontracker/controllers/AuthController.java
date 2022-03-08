package com.example.transactiontracker.controllers;

import com.example.transactiontracker.models.RoleType;
import com.example.transactiontracker.models.Role;
import com.example.transactiontracker.models.User;

import com.example.transactiontracker.payload.dto.LoginRequest;
import com.example.transactiontracker.payload.dto.SignupRequest;
import com.example.transactiontracker.payload.response.JwtResponse;
import com.example.transactiontracker.payload.response.MessageResponse;
import com.example.transactiontracker.security.jwt.JwtUtils;
import com.example.transactiontracker.services.authService.AuthService;
import com.example.transactiontracker.services.roleService.RoleService;
import com.example.transactiontracker.services.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4201", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    final private AuthService authService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }


}