package com.hadef.sakani.exceptions;

import com.hadef.sakani.domain.value.FailureEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class BadRequestException extends RuntimeException{
    private final FailureEnum event;
    private final String source;
    public BadRequestException(String message, FailureEnum event, String source) {
        super(message);
        this.event = event;
        this.source = source;
    }
}
