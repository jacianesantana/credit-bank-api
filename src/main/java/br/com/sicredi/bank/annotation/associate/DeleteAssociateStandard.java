package br.com.sicredi.bank.annotation.associate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Deletar um associado", description = "Realiza a exclusão de dados do associado.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Associado deletado com sucesso!",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "Associado não encontrado.",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "500", description = "Sistema indisponível.",
                content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
})
public @interface DeleteAssociateStandard {
}
