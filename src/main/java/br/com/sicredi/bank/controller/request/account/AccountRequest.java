package br.com.sicredi.bank.controller.request.account;

import br.com.sicredi.bank.entity.AssociateEntity;
import br.com.sicredi.bank.entity.TransactionEntity;
import br.com.sicredi.bank.entity.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    @NotNull(message = "Associado não pode ser nulo.")
    private AssociateEntity associate;

    @NotNull(message = "Tipo de Conta não pode ser nulo.")
    @Schema(example = "CORRENTE")
    private AccountType type;

    @NotNull(message = "Agência não pode ser nula.")
    @Schema(example = "1000")
    private Integer agency;

    @NotNull(message = "Número da Conta não pode ser nulo.")
    @Schema(example = "12345678")
    private Integer number;

    @NotNull(message = "Saldo não pode ser nulo.")
    @Schema(example = "1000.00")
    private BigDecimal balance;

    private Set<TransactionEntity> transactionSet;

}
