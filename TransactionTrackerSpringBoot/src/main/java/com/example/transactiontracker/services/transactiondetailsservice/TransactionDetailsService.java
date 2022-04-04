package com.example.transactiontracker.services.transactiondetailsservice;

import com.example.transactiontracker.models.transaction.TransactionDetail;
import com.example.transactiontracker.models.payload.dto.TransactionDetailsDTO;
import com.example.transactiontracker.models.payload.response.TransactionDetailResponse;

import java.util.Optional;

public interface TransactionDetailsService {
    TransactionDetail save(TransactionDetail transactionDetail);

    void deleteById(long id);

    Optional<TransactionDetail> findById(long id);

    TransactionDetail setTransactionDetailsAttributesAndReturnNewEntity(TransactionDetailsDTO transactionDetailsDTO, TransactionDetail transactionDetailsData);

    TransactionDetailResponse generateImageUrl(TransactionDetailResponse transactionDetailResponse);

    void updateProductInventory(TransactionDetailsDTO transactionDetailsDTO);

    TransactionDetailResponse update(TransactionDetail transactionDetail, TransactionDetailsDTO transactionDetailsDTO);
}
