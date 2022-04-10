package com.example.transactiontracker.controllers;

import com.example.transactiontracker.models.shift.Shift;
import com.example.transactiontracker.services.shiftservice.ShiftService;
import com.example.transactiontracker.services.userservice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftService shiftService;
    private final UserService userService;

    @PostMapping("/shifts")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Shift> createShift() {
        try {
            Shift shiftEntity = shiftService.createShift();
            return new ResponseEntity<>(shiftEntity, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
