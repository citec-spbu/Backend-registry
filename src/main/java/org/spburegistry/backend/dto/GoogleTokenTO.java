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
public class GoogleTokenTO {
    @SerializedName(value = "access_token")
    private String accessToken;
    @SerializedName(value = "expires_in")
    private int expirationTime;
    @SerializedName(value = "refresh_token")
    private String refreshToken;
    private String scope;
    @SerializedName(value = "token_type")
    private String tokenType;
}
