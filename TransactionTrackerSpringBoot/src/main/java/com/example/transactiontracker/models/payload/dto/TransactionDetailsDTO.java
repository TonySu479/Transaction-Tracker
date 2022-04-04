package com.example.transactiontracker.models.payload.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TransactionDetailsDTO {
    @NotBlank
    private Long id;

    @NotBlank
    private Long transactionId;

    @NotBlank
    private Long productId;

    @NotBlank
    private int quantity;

    @NotBlank
    private int price;
}
