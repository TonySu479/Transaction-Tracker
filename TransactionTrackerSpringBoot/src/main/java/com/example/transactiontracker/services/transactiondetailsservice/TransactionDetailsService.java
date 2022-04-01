package com.example.transactiontracker.services.transactiondetailsservice;

import com.example.transactiontracker.models.transaction.TransactionDetail;
import com.example.transactiontracker.payload.dto.TransactionDetailsDTO;

import java.util.Optional;

public interface TransactionDetailsService {
    TransactionDetail save(TransactionDetail transactionDetail);

    void deleteById(long id);

    Optional<TransactionDetail> findById(long id);

    TransactionDetail setTransactionDetailsAttributesAndReturnNewEntity(TransactionDetailsDTO transactionDetailsDTO, Optional<TransactionDetail> transactionDetailsData);

    TransactionDetail generateImageUrl(TransactionDetail transactionDetail);

}
