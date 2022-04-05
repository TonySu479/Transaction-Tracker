package com.example.transactiontracker.models.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Calendar;

@Data
@AllArgsConstructor
public class TransactionDTO {

    private Calendar createdAt;

    private Long id;

    private int total;
}
