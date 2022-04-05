package com.example.transactiontracker.services.transactionservice;

import com.example.transactiontracker.models.transaction.Transaction;
import com.example.transactiontracker.models.transaction.TransactionDetail;
import com.example.transactiontracker.services.repositories.TransactionDetailsRepository;
import com.example.transactiontracker.services.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionDetailsRepository transactionDetailsRepository;

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> findById(long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public void deleteAll() {
        transactionRepository.deleteAll();
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Transaction setTransactionAttributesAndReturnNewEntity(Transaction transaction, Optional<Transaction> transactionData) {
        Transaction transactionEntity = transactionData.get();
        transactionEntity.setCreatedAt(transaction.getCreatedAt());
        return transactionEntity;
    }

    @Override
    public List<TransactionDetail> findAllByTransaction_Id(long id) {
        return transactionDetailsRepository.findAllByTransaction_Id(id);
    }

    @Override
    public int getTransactionTotalFromTransaction(Transaction transaction){
        List<TransactionDetail> transactionDetailList = transactionDetailsRepository.findAllByTransaction_Id(transaction.getId());
        int total = 0;
        for(TransactionDetail transactionDetail : transactionDetailList) {
            total += transactionDetail.getQuantity() * transactionDetail.getPrice();
        }
        return total;
    }

    @Override
    public List<Transaction> findAllByCreatedAt(Date createdAt) {
        return transactionRepository.findAllByCreatedAt(createdAt);
    }

    @Override
    public List<Transaction> findTransactionsByCurrentDay() {
        Date date = new Date();
        return transactionRepository.findAllByCreatedAt(date);
    }

}
