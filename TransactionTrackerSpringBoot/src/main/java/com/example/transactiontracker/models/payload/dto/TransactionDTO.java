package com.example.transactiontracker.models.payload.dto;

import com.example.transactiontracker.models.transaction.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class TransactionDTO {

    private LocalDate createdAt;

    private Long id;

    private int total;

    private TransactionType transactionType;
}
