package com.example.transactiontracker.repositories;

import com.example.transactiontracker.models.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long> {
    List<TransactionDetails> findAllByTransaction_Id(long id);
}
