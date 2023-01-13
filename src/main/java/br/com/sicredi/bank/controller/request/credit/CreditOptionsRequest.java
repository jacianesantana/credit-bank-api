package br.com.sicredi.bank.controller.request.credit;

import br.com.sicredi.bank.entity.enums.ProductType;
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
public class CreditOptionsRequest {

    private ProductType productType;
    private LocalDate birthDate;
    private BigDecimal salary;

}
