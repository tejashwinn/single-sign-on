package com.tejashwin.admin.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class LoginResponse {

    private String accessToken;
    private String refreshToken;

    public LoginResponse(final KeycloakLoginResponse keycloakLoginResponse) {
        this.accessToken = keycloakLoginResponse.getAccessToken();
        this.refreshToken = keycloakLoginResponse.getRefreshToken();
    }

}
