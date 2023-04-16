package org.spburegistry.backend.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class JwtFilter extends GenericFilterBean {

    private static final String ACCESS_TOKEN_COOKIE_NAME = "access_token";
    private final JwtProvider jwtProvider;

    @Autowired
    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Optional<Cookie> accessTokenCookie = getAccessTokenCookie(servletRequest);
        if (accessTokenCookie.isPresent()) {
            String accessToken = accessTokenCookie.get().getValue();
            try {
                jwtProvider.validateAccessToken(accessToken);
                Claims claims = jwtProvider.getAccessTokenClaims(accessToken);
                JwtAuthentication authentication = getJwtAuthentication(claims);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (ExpiredJwtException exception) {
                refreshToken();
            } catch (Exception exception) {
                ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @NotNull
    private static Optional<Cookie> getAccessTokenCookie(ServletRequest request) {
        return Optional.ofNullable(((HttpServletRequest) request).getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> cookie.getName().equals(ACCESS_TOKEN_COOKIE_NAME))
                        .findFirst());
    }

    private static JwtAuthentication getJwtAuthentication(Claims claims) {
        String email = claims.getSubject();
        String name = claims.get("name", String.class);
        return JwtAuthentication.builder()
                .auth(true)
                .name(name)
                .email(email)
                .build();
    }

    private void refreshToken() {
    }
}
