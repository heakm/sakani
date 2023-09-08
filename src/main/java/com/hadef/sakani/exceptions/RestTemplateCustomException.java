package com.hadef.sakani.exceptions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResponseErrorHandler;

@Getter
@Setter
@EqualsAndHashCode
public class RestTemplateCustomException extends RuntimeException{
    private final String message;
    private final int errorCode;

    public RestTemplateCustomException(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}