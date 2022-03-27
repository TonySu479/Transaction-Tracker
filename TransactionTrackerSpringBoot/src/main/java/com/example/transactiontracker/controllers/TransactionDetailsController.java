package com.example.transactiontracker.controllers;

import com.example.transactiontracker.models.TransactionDetails;
import com.example.transactiontracker.payload.dto.TransactionDetailsDTO;
import com.example.transactiontracker.services.productservice.ProductService;
import com.example.transactiontracker.services.transactiondetailsservice.TransactionDetailsService;
import com.example.transactiontracker.services.transactionservice.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionDetailsController {

    private final TransactionDetailsService transactionDetailsService;
    private final TransactionService transactionService;
    private final ProductService productService;

    @PostMapping("/transaction-details")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<TransactionDetails> createTransactionDetails(@RequestBody TransactionDetailsDTO transactionDetails) {
        try {
            TransactionDetails transactionDetailsEntity = transactionDetailsService
                    .save(new TransactionDetails(transactionService.findById(transactionDetails.getTransactionId()).orElse(null), productService.findById(transactionDetails.getProductId()).orElse(null),
                            transactionDetails.getQuantity(), transactionDetails.getPrice()));
            return new ResponseEntity<>(transactionDetailsEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
