package org.spburegistry.backend.external_services;

import io.reactivex.rxjava3.core.Single;
import org.spburegistry.backend.dto.GoogleTokenRequestTO;
import org.spburegistry.backend.dto.GoogleTokenTO;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface GoogleAuthenticationService {
    @POST("/token")
    Single<GoogleTokenTO> getToken(@Body GoogleTokenRequestTO tokenRequestTO);
}
