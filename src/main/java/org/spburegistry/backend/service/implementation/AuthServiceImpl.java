package org.spburegistry.backend.service.implementation;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.spburegistry.backend.config.JwtProvider;
import org.spburegistry.backend.dto.AuthResponse;
import org.spburegistry.backend.dto.GoogleTokenRequestTO;
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
public class AuthServiceImpl implements AuthService {

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
    public AuthServiceImpl(GoogleAuthenticationService googleAuthenticationService,
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
    public Single<AuthResponse> authenticateUserByGoogle(String code) {
        return googleAuthenticationService.getToken(getGoogleTokenRequestTO(code))
                .subscribeOn(Schedulers.io())
                .flatMap(this::getUserInfo)
                .map(this::processUser)
                .onErrorReturn(error -> new AuthResponse("failure", null, null, error.getLocalizedMessage()));
    }

    private AuthResponse processUser(Pair<User, GoogleTokenTO> pair) {
        User user = pair.getFirst();
        GoogleTokenTO googleTokenTO = pair.getSecond();
        if (userRepo.findByEmail(user.getEmail()) == null){
            userService.createUser(user.getName(), user.getEmail());
        }
        return new AuthResponse("success", jwtProvider.generateAccessToken(user, googleTokenTO), jwtProvider.generateRefreshToken(user, googleTokenTO), null);
    }

    private Single<Pair<User, GoogleTokenTO>> getUserInfo(GoogleTokenTO accessToken) {
        return googleApiService.getGoogleUserInfo(accessToken.getAccessToken()).map(user -> Pair.of(user, accessToken));
    }

    private GoogleTokenRequestTO getGoogleTokenRequestTO(String code) {
        return GoogleTokenRequestTO.builder()
                .clientId(googleClientId)
                .clientSecret(googleClientSecret)
                .code(code)
                .grantType("authorization_code")
                .redirectUri("http://localhost:8080/testGoogle")
                .build();
    }


    @Override
    public Single<AuthResponse> authenticateUserBySpbu() {
        throw new UnsupportedOperationException("not yet implemented");
    }
}
