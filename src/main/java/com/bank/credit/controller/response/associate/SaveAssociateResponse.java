package com.bank.credit.controller.response.associate;

import com.bank.credit.controller.response.account.AccountResponse;
import com.bank.credit.model.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveAssociateResponse {

    @Schema(example = "1")
    private Long id;
    @Schema(example = "Jaciane Santana")
    private String name;
    @Schema(example = "012.345.678-90")
    private String cpf;
    private List<AccountResponse> accounts;
}
