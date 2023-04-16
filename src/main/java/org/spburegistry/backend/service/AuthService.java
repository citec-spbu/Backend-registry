package org.spburegistry.backend.service;

import io.reactivex.rxjava3.core.Single;
import org.spburegistry.backend.dto.AuthResponse;

public interface AuthService {
    Single<AuthResponse> authenticateUserByGoogle(String code);
    Single<AuthResponse> authenticateUserBySpbu();
}
