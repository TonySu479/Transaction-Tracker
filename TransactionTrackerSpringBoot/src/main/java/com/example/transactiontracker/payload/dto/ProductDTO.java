package com.example.transactiontracker.payload.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductDTO {

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @NotBlank
    private int price;

    @NotBlank
    private String unit;

    @NotBlank
    private String image;
}
