package br.com.sicredi.bank.annotation.product;

import br.com.sicredi.bank.controller.response.product.ProductResponse;
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
@Operation(summary = "Buscar produtos", description = "Realiza a busca de lista de produtos.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso!",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ProductResponse.class))),
        @ApiResponse(responseCode = "400", description = "Salário abaixo do permitido para contratação de produtos.",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "500", description = "Sistema indisponível.",
                content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
public @interface ProductStandard {
}