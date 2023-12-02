package com.tejashwin.admin.service;

import com.tejashwin.admin.dto.request.LoginRequest;
import com.tejashwin.admin.dto.response.KeycloakLoginResponse;
import com.tejashwin.admin.dto.response.LoginResponse;

public interface TokenService {
    LoginResponse login(LoginRequest loginRequest);
}
