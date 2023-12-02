package com.tejashwin.admin.controller;

import com.tejashwin.admin.dto.request.LoginRequest;
import com.tejashwin.admin.dto.response.KeycloakLoginResponse;
import com.tejashwin.admin.dto.response.LoginResponse;
import com.tejashwin.admin.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {

    private final TokenService tokenService;

    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return this.tokenService.login(loginRequest);
    }
}
