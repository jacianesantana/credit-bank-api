package br.com.sicredi.bank.controller.response.associate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAssociateResponse {

    @Schema(example = "Engenheiro")
    private String profession;

    @Schema(example = "5000.00")
    private BigDecimal salary;

}
