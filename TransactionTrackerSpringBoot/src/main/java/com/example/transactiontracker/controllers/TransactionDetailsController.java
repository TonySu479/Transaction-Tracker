package com.example.transactiontracker.controllers;

import com.example.transactiontracker.models.Product;
import com.example.transactiontracker.models.TransactionDetails;
import com.example.transactiontracker.services.transactiondetailsservice.TransactionDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionDetailsController {

    private final TransactionDetailsService transactionDetailsService;

    @GetMapping("/transaction-details")
    public ResponseEntity<List<TransactionDetails>> getAllTransactionDetails() {
        try {
            List<TransactionDetails> transactionDetails = new ArrayList<>();
            transactionDetails.addAll(transactionDetailsService.findAll());

            if (transactionDetails.isEmpty()) {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
            }
            return new ResponseEntity<>(transactionDetails, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
