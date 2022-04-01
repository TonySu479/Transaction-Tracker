package com.example.transactiontracker.payload.dto;

import com.example.transactiontracker.models.product.ProductCategory;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProductDTO {
    @NotBlank
    private String code;

    @NotBlank
    private String name;

    @NotBlank
    private ProductCategory category;

    @NotBlank
    private int price;

    @NotBlank
    private String unit;

    @NotBlank
    private String image;
}
