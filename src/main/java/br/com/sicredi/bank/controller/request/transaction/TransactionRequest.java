package br.com.sicredi.bank.controller.request.transaction;

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

    private DebitAccountRequest debitAccount;
    private CreditAccountRequest creditAccount;

    @Min(value = 1)
    private BigDecimal value;

}
