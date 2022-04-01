package com.example.transactiontracker.controllers;

import com.example.transactiontracker.models.TransactionDetails;
import com.example.transactiontracker.payload.dto.TransactionDetailsDTO;
import com.example.transactiontracker.repositories.TransactionDetailsRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionDetailsController {

    private final TransactionDetailsService transactionDetailsService;
    private final TransactionService transactionService;
    private final ProductService productService;
    private final TransactionDetailsRepository transactionDetailsRepository;

    @PostMapping("/transaction-details")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<TransactionDetails> createTransactionDetail(@RequestBody TransactionDetailsDTO transactionDetails) {
        try {
            TransactionDetails transactionDetailsEntity = transactionDetailsService
                    .save(new TransactionDetails(transactionService.findById(transactionDetails.getTransactionId()).orElse(null), productService.findById(transactionDetails.getProductId()).orElse(null),
                            transactionDetails.getQuantity(), transactionDetails.getPrice()));
            return new ResponseEntity<>(transactionDetailsEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/transaction-details/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<TransactionDetails> updateTransactionDetail(@PathVariable("id") long id, @RequestBody TransactionDetailsDTO transactionDetailsDTO) {
        Optional<TransactionDetails> TransactionDetailsData = transactionDetailsService.findById(id);
        if (TransactionDetailsData.isPresent()) {
            TransactionDetails transactionDetailsEntity = transactionDetailsService.setTransactionDetailsAttributesAndReturnNewEntity(transactionDetailsDTO, TransactionDetailsData);
            return new ResponseEntity<>(transactionDetailsService.save(transactionDetailsEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/transaction-details/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteTransactionDetail(@PathVariable("id") long id) {
        try {
            transactionDetailsService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/transaction-details")
    public ResponseEntity<List<TransactionDetails>> getTransactionDetailsByTransactionId(@RequestParam long id) {
        try {
            List<TransactionDetails> transactionDetails = new ArrayList<>(transactionDetailsRepository.findAllByTransaction_Id(id));
            if (transactionDetails.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(transactionDetails, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
