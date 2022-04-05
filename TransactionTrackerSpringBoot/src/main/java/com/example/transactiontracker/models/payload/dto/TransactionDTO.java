package com.example.transactiontracker.models.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TransactionDTO {

    private Date createdAt;

    private Long id;

    private int total;
}
