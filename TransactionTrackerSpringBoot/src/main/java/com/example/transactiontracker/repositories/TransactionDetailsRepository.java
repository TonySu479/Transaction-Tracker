package com.example.transactiontracker.repositories;

import com.example.transactiontracker.models.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long> {
    List<TransactionDetails> findAllByTransaction_Id(long id);
}
