package com.tzb.backend.common.exception;

import com.tzb.backend.common.constant.ExceptionEnum;
import com.tzb.backend.common.core.CustomException;
import com.tzb.backend.common.core.ResultWrapper;
import com.tzb.backend.common.exception.valid.ExceptionHandlingContext;
import com.tzb.backend.common.utils.ResultUtil;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @author 29002
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResultWrapper handleCustomException(CustomException e) {
        ExceptionEnum exceptionEnum = e.getExceptionEnum();
        if (Objects.nonNull(exceptionEnum)) {
            return ResultUtil.fail(e.getExceptionEnum().getCode(), e.getExceptionEnum().getMsg());
        }
        return ResultUtil.fail(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public ResultWrapper handleException(Exception e) {
        String msg = ExceptionHandlingContext.handleException(e);
        return ResultUtil.fail(1001, msg);
    }
}
