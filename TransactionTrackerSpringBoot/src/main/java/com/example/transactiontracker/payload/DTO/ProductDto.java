package com.example.transactiontracker.payload.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductDto {
    @NotBlank
    private String code;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private int price;

    @NotBlank
    private int quantity;

    @NotBlank
    private String unit;

    @NotBlank
    private String image;
}
