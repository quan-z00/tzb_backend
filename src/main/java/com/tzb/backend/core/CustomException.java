package com.tzb.backend.core;

import com.tzb.backend.constant.ExceptionEnum;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ExceptionEnum exceptionEnum;

    public CustomException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }
}
