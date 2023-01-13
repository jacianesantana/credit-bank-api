package br.com.sicredi.bank.controller.request.transaction;

import br.com.sicredi.bank.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    private AccountEntity account;
    @Min(value = 1)
    private BigDecimal value;
}
