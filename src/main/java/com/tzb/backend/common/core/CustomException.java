package com.tzb.backend.common.core;

import com.tzb.backend.common.constant.ExceptionEnum;
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
