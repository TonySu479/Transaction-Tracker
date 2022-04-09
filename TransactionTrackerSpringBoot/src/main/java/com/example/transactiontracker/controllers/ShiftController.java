package com.example.transactiontracker.controllers;

import com.example.transactiontracker.models.shift.Shift;
import com.example.transactiontracker.models.user.User;
import com.example.transactiontracker.security.jwt.JwtUtils;
import com.example.transactiontracker.services.shiftservice.ShiftService;
import com.example.transactiontracker.services.userservice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftService shiftService;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/shifts")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Shift> createShift() {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            String username = userDetails.getUsername();
            System.out.println(username);
            Shift shiftEntity = shiftService
                    .save(new Shift(new Date(), null, userService.findByUsername(username).orElseThrow(Exception::new), 0));
            return new ResponseEntity<>(shiftEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
