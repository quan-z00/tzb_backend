package com.tzb.backend.exception;

import com.tzb.backend.core.CustomException;
import com.tzb.backend.core.ResultWrapper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 29002
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResultWrapper handleCustomException(CustomException e) {
        ResultWrapper resultWrapper = new ResultWrapper();
        resultWrapper.fail(e.getExceptionEnum().getCode(), e.getExceptionEnum().getMsg());
        return resultWrapper;
    }
}
