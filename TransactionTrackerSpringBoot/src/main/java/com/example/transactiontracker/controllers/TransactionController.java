package com.example.transactiontracker.controllers;

import com.example.transactiontracker.models.transaction.Transaction;
import com.example.transactiontracker.models.payload.dto.TransactionDTO;
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
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") long id) {
        Optional<Transaction> transactionData = transactionService.findById(id);
        return transactionData.map(transaction -> new ResponseEntity<>(transaction, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        try {
            return new ResponseEntity<>(transactionService
                    .save(new Transaction(transaction.getCreatedAt(), transaction.getTransactionType())), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable("id") long id, @RequestBody Transaction transaction) {
        Optional<Transaction> transactionData = transactionService.findById(id);
        if (transactionData.isPresent()) {
            Transaction transactionEntity = transactionService.createTransaction(transaction, transactionData);
            return new ResponseEntity<>(transactionService.save(transactionEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteTransaction(@PathVariable("id") long id) {
        try {
            transactionService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        try {
            List<Transaction> transactions = transactionService.findAll();
            List<TransactionDTO> transactionDTOs = new ArrayList<>();
            if (transactions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            for (Transaction transaction : transactions) {
                transactionDTOs.add(new TransactionDTO(transaction.getCreatedAt(), transaction.getId(), transactionService.getTransactionTotalFromTransaction(transaction), transaction.getTransactionType()));
            }
            return new ResponseEntity<>(transactionDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete-transactions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteTransactions(@RequestBody List<String> listOfIds) {
        try {
            for (String id : listOfIds) {
                transactionService.deleteById(Long.parseLong(id));
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/transactions/totals")
    public ResponseEntity<List<TransactionDTO>> getTransactionTotals() {
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        for (Transaction transaction : transactionService.findAll()) {
            transactionDTOS.add(new TransactionDTO(transaction.getCreatedAt(), transaction.getId(), transactionService.getTransactionTotalFromTransaction(transaction), transaction.getTransactionType()));
        }
        return new ResponseEntity<>(transactionDTOS, HttpStatus.OK);
    }

    @GetMapping("/transactions/current-day")
    public ResponseEntity<List<Transaction>> getCurrentDayTransactions() {
        return new ResponseEntity<>(transactionService.findTransactionsByCurrentDay(), HttpStatus.OK);
    }

}
