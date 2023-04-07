package org.spburegistry.backend.ExceptionHandler.exception;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException(String message) {
        super(message);
    }
    
}
