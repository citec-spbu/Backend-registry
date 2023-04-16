package org.spburegistry.backend.external_services;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class ServicesConfiguration {
    private final static String GOOGLE_OAUTH_URL = "https://oauth2.googleapis.com";
    private final static String GOOGLE_API_URL = "https://www.googleapis.com";
    @Bean
    public GoogleAuthenticationService getGoogleAuthService() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GOOGLE_OAUTH_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(httpClient.build())
                .build();
        return retrofit.create(GoogleAuthenticationService.class);
    }

    @Bean
    public GoogleApiService getGoogleApiService() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GOOGLE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(httpClient.build())
                .build();
        return retrofit.create(GoogleApiService.class);
    }
}
