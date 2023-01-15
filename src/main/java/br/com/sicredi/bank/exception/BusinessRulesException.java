package br.com.sicredi.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessRulesException extends RuntimeException {

    public BusinessRulesException(String message) {
        super(message);
    }

}
