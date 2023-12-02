package com.tejashwin.admin.service;

import com.tejashwin.admin.dto.request.LoginRequest;
import com.tejashwin.admin.dto.response.LoginResponse;
import com.tejashwin.admin.proxy.KeycloakProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.tejashwin.admin.constant.KeycloakConstants.CLIENT_ID;
import static com.tejashwin.admin.constant.KeycloakConstants.CLIENT_SECRET;
import static com.tejashwin.admin.constant.KeycloakConstants.GRANT_TYPE;
import static com.tejashwin.admin.constant.KeycloakConstants.PASSWORD;
import static com.tejashwin.admin.constant.KeycloakConstants.USERNAME;

@Service
public class TokenServiceImpl implements TokenService {

    private final KeycloakProxy keycloakProxy;

    private final String clientId;

    private final String clientSecret;

    public TokenServiceImpl(final KeycloakProxy keycloakProxy,
                            @Value("${admin.service.keycloak.client-id}") final String clientId,
                            @Value("${admin.service.keycloak.client-secret}") final String clientSecret) {
        this.keycloakProxy = keycloakProxy;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }


    @Override
    public LoginResponse login(final LoginRequest loginRequest) {
        final Map<String, String> keycloakLoginForm = new HashMap<>();
        keycloakLoginForm.put(CLIENT_ID, this.clientId);
        keycloakLoginForm.put(CLIENT_SECRET, this.clientSecret);
        keycloakLoginForm.put(USERNAME, loginRequest.getUsername());
        keycloakLoginForm.put(PASSWORD, loginRequest.getPassword());
        keycloakLoginForm.put(GRANT_TYPE, PASSWORD);
        return new LoginResponse(this.keycloakProxy.login(keycloakLoginForm));
    }

}
