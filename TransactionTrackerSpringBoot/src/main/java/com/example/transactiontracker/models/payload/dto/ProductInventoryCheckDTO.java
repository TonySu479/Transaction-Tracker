package com.example.transactiontracker.models.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ProductInventoryCheckDTO {

    @NotBlank
    private String code;

    @NotBlank
    private int quantity;
}
