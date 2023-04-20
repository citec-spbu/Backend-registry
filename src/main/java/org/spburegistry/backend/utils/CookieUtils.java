package org.spburegistry.backend.utils;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CookieUtils {
    @NotNull
    public static Optional<Cookie> getTokenCookie(ServletRequest request, String cookieName) {
        return Optional.ofNullable(((HttpServletRequest) request).getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> cookie.getName().equals(cookieName))
                        .findFirst());
    }
}
