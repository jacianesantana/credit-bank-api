package br.com.sicredi.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AssociateRulesException extends RuntimeException {

    public AssociateRulesException(String message) {
        super(message);
    }

}
