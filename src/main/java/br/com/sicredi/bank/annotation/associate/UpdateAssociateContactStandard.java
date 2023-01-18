package br.com.sicredi.bank.annotation.associate;

import br.com.sicredi.bank.controller.response.associate.UpdateAssociateContactResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Atualizar contato de um associado", description = "Realiza a atualização de contato do associado.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contato atualizado com sucesso!",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = UpdateAssociateContactResponse.class))),
        @ApiResponse(responseCode = "400", description = "Campo nulo, ou preenchido de forma incorreta, tente de novo.",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "Associado não encontrado.",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "500", description = "Sistema indisponível.",
                content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
public @interface UpdateAssociateContactStandard {
}
