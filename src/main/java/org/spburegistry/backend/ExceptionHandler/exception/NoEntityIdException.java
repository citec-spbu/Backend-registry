package org.spburegistry.backend.ExceptionHandler.exception;

public class NoEntityIdException extends RuntimeException {

    public NoEntityIdException(String message) {
        super(message);
    }
}
