package com.example.transactiontracker.repositories;

import com.example.transactiontracker.models.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long> {

}
