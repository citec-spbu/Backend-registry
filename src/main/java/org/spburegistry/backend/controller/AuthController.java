package org.spburegistry.backend.controller;

import io.reactivex.rxjava3.core.Single;
import org.spburegistry.backend.dto.AuthResponse;
import org.spburegistry.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping
    public Single<AuthResponse> authenticateUser(@RequestParam String code) {
        return authService.authenticateUser(code);
    }

    @GetMapping("/refreshToken")
    public Single<AuthResponse> refreshToken(@CookieValue("refresh_token") String refreshToken) {
        return authService.refreshAccessToken(refreshToken);
    }
}
