package org.spburegistry.backend.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleTokenRequestTO {
    private String code;
    @SerializedName(value = "client_id")
    private String clientId;
    @SerializedName(value = "client_secret")
    private String clientSecret;
    @SerializedName(value = "redirect_uri")
    private String redirectUri;
    @SerializedName(value = "grant_type")
    private String grantType;
}
