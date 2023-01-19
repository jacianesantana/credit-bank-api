package br.com.sicredi.bank.controller.response.transaction;

import br.com.sicredi.bank.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatementTransaction {

    private TransactionType type;
    private BigDecimal value;
    private LocalDateTime createdAt;
}
