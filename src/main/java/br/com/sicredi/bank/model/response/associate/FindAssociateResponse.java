package br.com.sicredi.bank.model.response.associate;

import br.com.sicredi.bank.model.response.account.AccountResponse;
import br.com.sicredi.bank.model.response.address.AddressResponse;
import br.com.sicredi.bank.model.response.contract.ListContractResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindAssociateResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Jaciane Santana")
    private String name;

    @Schema(example = "012.345.678-90")
    private String cpf;

    @Schema(example = "1993-10-19")
    private LocalDate birthDate;

    @Schema(example = "71999999999")
    private String phone;

    @Schema(example = "nome.sobrenome@mail.com")
    private String email;

    @Schema(example = "Engenheiro de Software")
    private String profession;

    @Schema(example = "4000.00")
    private BigDecimal salary;

    @Schema(example = "2022-01-10")
    private LocalDate lastPaycheck;

    private List<AddressResponse> addresses;

    private List<AccountResponse> accounts;

    private List<ListContractResponse> contracts;

}
