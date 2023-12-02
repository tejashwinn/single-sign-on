package com.tejashwin.admin.proxy;

import com.tejashwin.admin.dto.response.KeycloakLoginResponse;
import com.tejashwin.admin.exception.CustomErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(name = "keycloak", url = "${admin.service.keycloak.address}", configuration = {CustomErrorDecoder.class})
public interface KeycloakProxy {

    @PostMapping(value = "/realms/${admin.service.keycloak.realm-name}/protocol/openid-connect/token",
            consumes = APPLICATION_FORM_URLENCODED_VALUE,
            produces = APPLICATION_JSON_VALUE)
    KeycloakLoginResponse login(Map<String, ?> keycloakLoginForm);

}
