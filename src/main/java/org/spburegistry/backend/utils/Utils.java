package org.spburegistry.backend.utils;

import org.spburegistry.backend.ExceptionHandler.exception.NegativeLimitException;

public class Utils {
    private Utils() {
    }

    public static void checkLimit(long lim) {
        if (lim < 0) {
            throw new NegativeLimitException("Limit has to be positive, actual limit is " + lim);
        }
    }
}
