package com.oracle.oraclewithspringboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransactionRequestDTO {
    @NotBlank private String toAccount;
    @NotBlank private String fromAccount;
    @Positive private Double amount;
}
