package com.example.transactiontracker.models.payload.dto;

import com.example.transactiontracker.models.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionDTO {

    private Transaction transaction;

    private int total;
}
