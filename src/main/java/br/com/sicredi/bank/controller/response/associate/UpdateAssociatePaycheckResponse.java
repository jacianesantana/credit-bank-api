package br.com.sicredi.bank.controller.response.associate;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class UpdateAssociatePaycheckResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Engenheiro de Software")
    private String profession;

    @Schema(example = "4000.00")
    private BigDecimal salary;

    @Schema(example = "2022-01-10")
    private LocalDate lastPaycheck;

}
