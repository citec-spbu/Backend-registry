package org.spburegistry.backend.external_services;

import io.reactivex.rxjava3.core.Single;
import org.spburegistry.backend.entity.User;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleApiService {
    @GET("/oauth2/v3/userinfo")
    Single<User> getGoogleUserInfo(@Query("access_token") String accessToken);
}
