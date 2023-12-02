package com.tejashwin.admin.exception;

import com.tejashwin.admin.constant.ErrorCode;
import lombok.Getter;

@Getter
public class RequestException extends RuntimeException {

    private final ErrorCode errorCode;

    public RequestException(final ErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
    }

    public static class UnauthorizedException extends RequestException {

        public UnauthorizedException(ErrorCode errorCode) {
            super(errorCode);
        }
    }
}
