package net.burnbook.app.infra.exception.model;

public class ConflitoException extends RuntimeException{

    public ConflitoException(String message) {
        super(message);
    }
}
