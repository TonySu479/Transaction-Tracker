package com.example.transactiontracker.services.transactiondetailsservice;

import com.example.transactiontracker.models.transaction.TransactionDetails;
import com.example.transactiontracker.payload.dto.TransactionDetailsDTO;

import java.util.Optional;

public interface TransactionDetailsService {
    TransactionDetails save(TransactionDetails transactionDetails);

    void deleteById(long id);

    Optional<TransactionDetails> findById(long id);

    TransactionDetails setTransactionDetailsAttributesAndReturnNewEntity(TransactionDetailsDTO transactionDetailsDTO, Optional<TransactionDetails> transactionDetailsData);
}
