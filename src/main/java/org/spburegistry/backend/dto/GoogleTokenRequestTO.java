package org.spburegistry.backend.dto;

import com.google.gson.annotations.SerializedName;


public class GoogleTokenRequestTO {

    @SerializedName(value = "client_id")
    private String clientId;
    @SerializedName(value = "client_secret")
    private String clientSecret;
    @SerializedName(value = "grant_type")
    private String grantType;

    public GoogleTokenRequestTO(String clientId, String clientSecret, String grantType) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.grantType = grantType;
    }
}
