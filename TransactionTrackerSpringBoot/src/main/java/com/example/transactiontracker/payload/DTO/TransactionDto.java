package com.example.transactiontracker.payload.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TransactionDto {
    @NotBlank
    private String title;

    @NotBlank
    private String description;
}
