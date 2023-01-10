package com.bank.credit.controller.request.associate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveAssociateRequest {
    private String name;
    private String cpf;
    private LocalDate birthDate;
    private String profession;
    private BigDecimal salary;
}
