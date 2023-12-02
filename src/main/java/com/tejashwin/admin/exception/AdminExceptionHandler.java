package com.tejashwin.admin.exception;

import com.tejashwin.admin.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@RequiredArgsConstructor
public class AdminExceptionHandler {

    private final MessageSource messageSource;

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(value = RequestException.class)
    public ErrorResponse requestException(final RequestException exception,
                                          final HttpServletRequest request) {
        return new ErrorResponse(
                exception.getErrorCode(),
                request.getRequestURI(),
                messageSource.getMessage(exception.getErrorCode().name(), null, LocaleContextHolder.getLocale()),
                null);
    }

}
