package com.example.transactiontracker.services.transactionservice;

import com.example.transactiontracker.models.transaction.Transaction;
import com.example.transactiontracker.models.transaction.TransactionDetails;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction save(Transaction transaction);

    void deleteAll();

    void deleteById(long id);

    Optional<Transaction> findById(long id);

    List<Transaction> findAll();

    Transaction setTransactionAttributesAndReturnNewEntity(Transaction transaction, Optional<Transaction> transactionData);

    List<TransactionDetails> findAllByTransaction_Id(long id);
}
