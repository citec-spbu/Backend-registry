package org.spburegistry.backend.ExceptionHandler.exception;

public class NegativeLimitException extends RuntimeException {
    public NegativeLimitException(String message) {
        super(message);
    }
}
