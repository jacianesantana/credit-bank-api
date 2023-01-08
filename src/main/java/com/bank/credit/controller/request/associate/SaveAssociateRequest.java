package com.bank.credit.controller.request.associate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveAssociateRequest {
    private String name;
    private String cpf;
    private String birthDate;
    private String profession;
    private String salary;
}
