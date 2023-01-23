package br.com.sicredi.bank.annotation.transaction;

import br.com.sicredi.bank.model.response.transaction.TransactionResponse;
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

import static br.com.sicredi.bank.utils.Message.*;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Transfer between accounts", description = "Performs a transfer between two accounts.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = TRANSFER_SUCCESS,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = TransactionResponse.class))),
        @ApiResponse(responseCode = "400", description = BAD_REQUEST_ERROR,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = ACCOUNT_FIND_ERROR,
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "500", description = SERVER_ERROR,
                content=@Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
public @interface TransferTransactionStandard {
}
