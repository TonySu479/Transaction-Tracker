package com.example.transactiontracker.payload.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductDto {
    @NotBlank
    private String name;

    @NotBlank
    private String unit;

    @NotBlank
    private int amount;
}
