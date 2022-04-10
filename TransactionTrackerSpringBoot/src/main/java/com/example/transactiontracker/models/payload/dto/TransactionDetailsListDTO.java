package com.example.transactiontracker.models.payload.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class TransactionDetailsListDTO {
    @NotBlank
    List<TransactionDetailsDTO> transactionDetailsDTOS;

    @NotBlank
    Long shiftId;
}
