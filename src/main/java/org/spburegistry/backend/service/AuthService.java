package org.spburegistry.backend.service;

import io.reactivex.rxjava3.core.Single;
import org.spburegistry.backend.dto.AuthResponse;

public interface AuthService {
    Single<AuthResponse> authenticateUser(String code);
    Single<AuthResponse> refreshAccessToken(String refreshToken);
}
