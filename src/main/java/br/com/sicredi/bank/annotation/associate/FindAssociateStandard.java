package br.com.sicredi.bank.annotation.associate;

import br.com.sicredi.bank.model.response.associate.FindAssociateResponse;
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

import static br.com.sicredi.bank.model.Message.*;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Find a associate", description = "Performs a associate data find.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = ASSOCIATE_FIND_SUCCESS,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = FindAssociateResponse.class))),
        @ApiResponse(responseCode = "404", description = ASSOCIATE_ERROR,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "500", description = SERVER_ERROR,
                content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
public @interface FindAssociateStandard {
}
