package org.spburegistry.backend.ExceptionHandler.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppError {
    private int statusCode;
    private String message;
}
