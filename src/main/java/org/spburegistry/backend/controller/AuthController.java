package org.spburegistry.backend.controller;

import io.reactivex.rxjava3.core.Single;
import org.spburegistry.backend.dto.AuthResponse;
import org.spburegistry.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/auth")
    public Single<AuthResponse> authenticateUser(@RequestParam String code) {
        return authService.authenticateUserByGoogle(code);
    }
}
