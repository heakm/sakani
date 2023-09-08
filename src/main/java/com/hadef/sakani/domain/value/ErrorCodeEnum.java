package com.hadef.sakani.domain.value;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum ErrorCodeEnum {

    DEFAULT("Service failure","001","Internal service error"),
    TITLE_IS_EMPTY("Title empty","1000","Title should not be empty"),
    SLUG_TITLE_IS_EMPTY("SlugTitle empty","1000","Slug title should not be empty");
    private final String key;
    private final String code;
    private final String description;

    ErrorCodeEnum(String key, String code, String description) {
        this.key = key;
        this.code = code;
        this.description = description;
    }

    public static String getErrorCode(String key){
        Optional<ErrorCodeEnum> enumOptional = Arrays.stream(ErrorCodeEnum.values()).filter(
                errorCodeEnum -> errorCodeEnum.code.equals(key)
        ).findFirst();
        if(enumOptional.isPresent()){
            return enumOptional.get().code;
        }
        return DEFAULT.code;
    }

    public static String getErrorMessage(String key){
        Optional<ErrorCodeEnum> enumOptional = Arrays.stream(ErrorCodeEnum.values()).filter(
                errorCodeEnum -> errorCodeEnum.code.equals(key)
        ).findFirst();
        if(enumOptional.isPresent()){
            return enumOptional.get().description;
        }
        return DEFAULT.description;
    }
}
