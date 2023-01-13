package br.com.sicredi.bank.exception;

public class DeleteEntityException extends RuntimeException {
    public DeleteEntityException(String message) {
        super(message);
    }
}
