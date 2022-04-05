package com.example.transactiontracker.services.repositories;

import com.example.transactiontracker.models.transaction.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetail, Long> {
    List<TransactionDetail> findAllByTransaction_Id(long id);
}
