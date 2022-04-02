package com.example.transactiontracker.payload.response;

import com.example.transactiontracker.models.product.Product;
import com.example.transactiontracker.models.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class TransactionDetailResponse {
    @NotBlank
    private Transaction transaction;

    @NotBlank
    private Product product;

    @NotBlank
    private int quantity;

    @NotBlank
    private int price;
    
}
