package com.bank.credit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AssociateRulesException extends RuntimeException {

    public AssociateRulesException(String message) {
        super(message);
    }

}
