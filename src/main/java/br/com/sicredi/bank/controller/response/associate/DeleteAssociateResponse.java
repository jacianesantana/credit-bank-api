package br.com.sicredi.bank.controller.response.associate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteAssociateResponse {

    private Boolean deleted;
    private String message;

}
