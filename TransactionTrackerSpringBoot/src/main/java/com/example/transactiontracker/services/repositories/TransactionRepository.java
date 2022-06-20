package com.example.transactiontracker.services.repositories;

import com.example.transactiontracker.models.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByCreatedAt(LocalDateTime createdAt);

    List<Transaction> findAllByShift_Id(Long id);
}
