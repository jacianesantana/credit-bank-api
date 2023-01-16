package br.com.sicredi.bank.controller.response.contract;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractResponse {

    @Schema(example = "1")
    private Long idContract;

}
