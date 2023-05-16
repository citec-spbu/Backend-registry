package org.spburegistry.backend.ExceptionHandler.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenError extends AppError {
    private String path;

    public TokenError(int statusCode, String message, String path) {
        super(statusCode, message);
        this.path = path;
    }
}
