package com.example.transactiontracker.services.transactiondetailsservice;

import com.example.transactiontracker.models.TransactionDetails;

import java.util.List;
import java.util.Optional;

public interface TransactionDetailsService {
    TransactionDetails save(TransactionDetails transactionDetails);
    void deleteById(long id);
    Optional<TransactionDetails> findById(long id);
    List<TransactionDetails> findAll();
}
