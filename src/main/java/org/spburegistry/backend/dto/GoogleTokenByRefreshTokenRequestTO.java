package org.spburegistry.backend.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;

public class GoogleTokenByRefreshTokenRequestTO extends GoogleTokenRequestTO{

    @SerializedName("refresh_token")
    private String refreshToken;

    @Builder
    public GoogleTokenByRefreshTokenRequestTO(String clientId, String clientSecret, String grantType, String refreshToken) {
        super(clientId, clientSecret, grantType);
        this.refreshToken = refreshToken;
    }
}
