package br.com.sicredi.bank.controller.request.account;

import br.com.sicredi.bank.entity.AssociateEntity;
import br.com.sicredi.bank.entity.TransactionEntity;
import br.com.sicredi.bank.entity.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    private AssociateEntity associate;
    private AccountType type;
    private Integer agency;
    private Integer number;
    private BigDecimal balance;
    private Set<TransactionEntity> transactionSet;
}
