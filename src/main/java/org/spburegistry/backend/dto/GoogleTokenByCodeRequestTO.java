package org.spburegistry.backend.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;


public class GoogleTokenByCodeRequestTO extends GoogleTokenRequestTO {

    private String code;
    @SerializedName(value = "redirect_uri")
    private String redirectUri;
    @Builder
    public GoogleTokenByCodeRequestTO(String clientId, String clientSecret, String grantType, String code, String redirectUri) {
        super(clientId, clientSecret, grantType);
        this.code = code;
        this.redirectUri = redirectUri;
    }
}
