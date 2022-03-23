package com.example.transactiontracker.services.transactionservice;

import com.example.transactiontracker.models.Transaction;
import com.example.transactiontracker.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findByNameContaining(String name) {
        return transactionRepository.findByNameContaining(name);
    }

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
    public Transaction getTransaction(Transaction transaction, Optional<Transaction> transactionData) {
        Transaction transactionEntity = transactionData.get();
        transactionEntity.setName(transaction.getName());
        transactionEntity.setDescription(transaction.getDescription());
        return transactionEntity;
    }
}
