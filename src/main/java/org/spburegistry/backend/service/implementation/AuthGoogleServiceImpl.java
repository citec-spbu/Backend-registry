package org.spburegistry.backend.service.implementation;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.spburegistry.backend.ExceptionHandler.exception.GoogleAuthException;
import org.spburegistry.backend.config.JwtProvider;
import org.spburegistry.backend.dto.AuthResponse;
import org.spburegistry.backend.dto.GoogleTokenByCodeRequestTO;
import org.spburegistry.backend.dto.GoogleTokenByRefreshTokenRequestTO;
import org.spburegistry.backend.dto.GoogleTokenTO;
import org.spburegistry.backend.entity.User;
import org.spburegistry.backend.external_services.GoogleApiService;
import org.spburegistry.backend.external_services.GoogleAuthenticationService;
import org.spburegistry.backend.repository.UserRepo;
import org.spburegistry.backend.service.AuthService;
import org.spburegistry.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class AuthGoogleServiceImpl implements AuthService {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    private final GoogleAuthenticationService googleAuthenticationService;
    private final GoogleApiService googleApiService;
    private final UserRepo userRepo;
    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthGoogleServiceImpl(GoogleAuthenticationService googleAuthenticationService,
                                 GoogleApiService googleApiService,
                                 UserRepo userRepo,
                                 UserService userService,
                                 JwtProvider jwtProvider) {
        this.googleAuthenticationService = googleAuthenticationService;
        this.googleApiService = googleApiService;
        this.userRepo = userRepo;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }
    @Override
    public Single<AuthResponse> authenticateUser(String code) {
        return googleAuthenticationService.getToken(getGoogleTokenByCodeRequestTO(code))
                .subscribeOn(Schedulers.io())
                .flatMap(this::getUserInfo)
                .map(this::processUser)
                .onErrorResumeNext(error -> Single.error(new GoogleAuthException(error.getMessage())));
    }

    @Override
    public Single<AuthResponse> refreshAccessToken(String refreshToken) {
        jwtProvider.validateRefreshToken(refreshToken);
        String googleRefreshToken = jwtProvider.getRefreshTokenClaims(refreshToken).get("googleRefreshToken", String.class);
        return googleApiService.getGoogleAccessToken(getGoogleTokenByRefreshTokenRequestTO(googleRefreshToken))
                .subscribeOn(Schedulers.io())
                .flatMap(this::getUserInfo)
                .map(this::processUser)
                .map(authResponse -> {
                    authResponse.setRefreshToken(null);
                    return authResponse;
                })
                .onErrorResumeNext(error -> Single.error(new GoogleAuthException(error.getMessage())));
    }

    private AuthResponse processUser(Pair<User, GoogleTokenTO> pair) {
        User user = pair.getFirst();
        GoogleTokenTO googleTokenTO = pair.getSecond();
        if (userRepo.findByEmail(user.getEmail()) == null){
            userService.createUser(user.getName(), user.getEmail());
        }
        return AuthResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(user, googleTokenTO))
                .refreshToken(jwtProvider.generateRefreshToken(user, googleTokenTO))
                .build();
    }

    private Single<Pair<User, GoogleTokenTO>> getUserInfo(GoogleTokenTO accessToken) {
        return googleApiService.getGoogleUserInfo(accessToken.getAccessToken()).map(user -> Pair.of(user, accessToken));
    }

    private GoogleTokenByCodeRequestTO getGoogleTokenByCodeRequestTO(String code) {
        return GoogleTokenByCodeRequestTO.builder()
                .clientId(googleClientId)
                .clientSecret(googleClientSecret)
                .code(code)
                .grantType("authorization_code")
                .redirectUri("http://localhost:8080/testGoogle")
                .build();
    }

    private GoogleTokenByRefreshTokenRequestTO getGoogleTokenByRefreshTokenRequestTO(String refreshToken) {
        return GoogleTokenByRefreshTokenRequestTO.builder()
                .clientId(googleClientId)
                .clientSecret(googleClientSecret)
                .refreshToken(refreshToken)
                .grantType("refresh_token")
                .build();
    }
}
