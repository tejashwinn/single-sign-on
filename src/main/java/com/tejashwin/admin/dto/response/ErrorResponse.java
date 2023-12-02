package com.tejashwin.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tejashwin.admin.constant.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@RequiredArgsConstructor
@JsonInclude(NON_NULL)
public class ErrorResponse {

    private final ErrorCode errorCode;
    private final String uri;
    private final String errorMessage;
    private final String detailedMessage;


}
