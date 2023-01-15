package br.com.sicredi.bank.controller.request.contract;

import br.com.sicredi.bank.entity.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractRequest {

    private Long idAssociate;
    private Long idAccount;
    private ProductType productType;
    private Integer numberOfInstallments;
    private BigDecimal value;

}
