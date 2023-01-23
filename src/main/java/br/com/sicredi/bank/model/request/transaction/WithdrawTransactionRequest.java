package br.com.sicredi.bank.model.request.transaction;

import br.com.sicredi.bank.model.request.account.AccountRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static br.com.sicredi.bank.utils.MessageValidation.TRANSACTION_VALUE_INVALID;
import static br.com.sicredi.bank.utils.MessageValidation.TRANSACTION_VALUE_NOT_NULL;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawTransactionRequest {

    private AccountRequest debitAccount;

    @NotNull(message = TRANSACTION_VALUE_NOT_NULL)
    @Min(value = 1, message = TRANSACTION_VALUE_INVALID)
    @Schema(example = "1000.00")
    private BigDecimal value;

}
