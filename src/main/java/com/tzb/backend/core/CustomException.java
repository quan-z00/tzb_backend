package com.tzb.backend.core;

import com.tzb.backend.constant.ExceptionEnum;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private ExceptionEnum exceptionEnum;
    private String msg;
    private int code;

    public CustomException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public CustomException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }
}
