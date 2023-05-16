package org.spburegistry.backend.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.spburegistry.backend.ExceptionHandler.error.TokenError;
import org.spburegistry.backend.utils.PropertiesParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Optional;

import static org.spburegistry.backend.utils.CookieUtils.getTokenCookie;

@Component
public class JwtFilter extends GenericFilterBean {

    private static final String ACCESS_TOKEN_COOKIE_NAME = "access_token";
    private final JwtProvider jwtProvider;
    private final PropertiesParser propertiesParser;

    @Autowired
    public JwtFilter(JwtProvider jwtProvider, PropertiesParser propertiesParser) {
        this.jwtProvider = jwtProvider;
        this.propertiesParser = propertiesParser;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (isPermittedPath((HttpServletRequest) servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        Optional<Cookie> accessTokenCookie = getTokenCookie(servletRequest, ACCESS_TOKEN_COOKIE_NAME);
        if (accessTokenCookie.isPresent()) {
            String accessToken = accessTokenCookie.get().getValue();
            try {
                jwtProvider.validateAccessToken(accessToken);
                Claims claims = jwtProvider.getAccessTokenClaims(accessToken);
                JwtAuthentication authentication = getJwtAuthentication(claims);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception exception) {
                processException((HttpServletRequest) servletRequest, servletResponse, exception);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void processException(HttpServletRequest servletRequest, ServletResponse servletResponse, Exception exception) throws IOException {
        TokenError tokenError = new TokenError(HttpStatus.UNAUTHORIZED.value(), exception.getMessage(), servletRequest.getServletPath());
        servletResponse.getWriter().write(convertObjectToJson(tokenError));
        servletResponse.setContentType("application/json");
    }

    public static String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
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

    private boolean isPermittedPath(HttpServletRequest request) {
        return propertiesParser.getPermittedPaths().stream().anyMatch(request.getServletPath()::startsWith);
    }
}
