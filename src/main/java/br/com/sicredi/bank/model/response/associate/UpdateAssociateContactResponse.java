package br.com.sicredi.bank.model.response.associate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAssociateContactResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "71999999999")
    private String phone;

    @Schema(example = "nome.sobrenome@mail.com")
    private String email;

}
