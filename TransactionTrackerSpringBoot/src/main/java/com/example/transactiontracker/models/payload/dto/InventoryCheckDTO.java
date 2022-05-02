package com.example.transactiontracker.models.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class InventoryCheckDTO {

    @NotBlank
    private String code;

    @NotBlank String name;

    @NotBlank
    private int quantity;
}
