package org.spburegistry.backend.ExceptionHandler.exception;

public class NoClientIdException extends RuntimeException {

    public NoClientIdException(String message) {
        super(message);
    }
}
