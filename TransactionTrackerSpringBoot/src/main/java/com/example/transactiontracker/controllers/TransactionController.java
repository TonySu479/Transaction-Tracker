package com.example.transactiontracker.controllers;

import com.example.transactiontracker.models.Transaction;
import com.example.transactiontracker.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4201")
@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions(@RequestParam(required = false) String title) {
        try {
            List<Transaction> transactions = new ArrayList<>();
            if (title == null)
                transactionRepository.findAll().forEach(transactions::add);
            else
                transactionRepository.findByTitleContaining(title).forEach(transactions::add);
            if (transactions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") long id) {
        Optional<Transaction> transactionData = transactionRepository.findById(id);
        if (transactionData.isPresent()) {
            return new ResponseEntity<>(transactionData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/transactions")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        try {
            Transaction _transaction = transactionRepository
                    .save(new Transaction(transaction.getTitle(), transaction.getDescription()));
            return new ResponseEntity<>(_transaction, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/transactions/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable("id") long id, @RequestBody Transaction transaction) {
        Optional<Transaction> transactionData = transactionRepository.findById(id);
        if (transactionData.isPresent()) {
            Transaction _transaction = transactionData.get();
            _transaction.setTitle(transaction.getTitle());
            _transaction.setDescription(transaction.getDescription());
            return new ResponseEntity<>(transactionRepository.save(_transaction), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/transactions/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteTransaction(@PathVariable("id") long id) {
        try {
            transactionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/transactions")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteAllTransactions() {
        try {
            transactionRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
