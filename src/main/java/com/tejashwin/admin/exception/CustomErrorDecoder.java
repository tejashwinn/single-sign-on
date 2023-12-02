package com.tejashwin.admin.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

import static com.tejashwin.admin.constant.ErrorCode.AA_OO1;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 401:
                return new RequestException.UnauthorizedException(AA_OO1);
            default:
                return new Exception("Keycloak service unavailable");
        }
    }
}