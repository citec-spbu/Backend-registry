package org.spburegistry.backend.ExceptionHandler.exception;

public class GoogleAuthException extends RuntimeException {
    public GoogleAuthException(String message) {
        super(message);
    }
}
