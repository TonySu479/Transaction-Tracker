package com.example.transactiontracker.services.transactionService;

import com.example.transactiontracker.models.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<Transaction> findByTitleContaining(String title);
    Transaction save(Transaction transaction);
    Optional<Transaction> findById(long id);
    void deleteAll();
    List<Transaction> findAll();
    void deleteById(long id);
}
