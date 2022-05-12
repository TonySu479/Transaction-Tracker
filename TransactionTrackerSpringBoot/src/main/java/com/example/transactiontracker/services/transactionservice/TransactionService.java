package com.example.transactiontracker.services.transactionservice;

import com.example.transactiontracker.models.transaction.Transaction;
import com.example.transactiontracker.models.transaction.TransactionDetail;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction save(Transaction transaction);

    void deleteAll();

    void deleteById(long id);

    Optional<Transaction> findById(long id);

    List<Transaction> findAll();

    Transaction createTransaction(Transaction transaction, Optional<Transaction> transactionData);

    List<TransactionDetail> findAllByTransaction_Id(long id);

    int getTransactionTotalFromTransaction(Transaction transaction);

    List<Transaction> findAllByCreatedAt(LocalDate createdAt);

    List<Transaction> findTransactionsByCurrentDay();

    List<Transaction> findTransactionsByShiftId(Long id);
}
