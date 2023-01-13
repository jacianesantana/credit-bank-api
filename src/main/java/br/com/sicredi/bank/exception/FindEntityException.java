package br.com.sicredi.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FindEntityException extends RuntimeException {

    public FindEntityException(String message) {
        super(message);
    }

}
