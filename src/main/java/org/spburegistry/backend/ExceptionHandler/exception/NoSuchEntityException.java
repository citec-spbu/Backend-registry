package org.spburegistry.backend.ExceptionHandler.exception;

public class NoSuchEntityException extends RuntimeException {

    public NoSuchEntityException(String message) {
        super(message);
    }
}
