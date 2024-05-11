package com.tzb.backend.common.exception.valid;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

@Component
public class BindExceptionStrategy implements ExceptionHandlingStrategy {
    @Override
    public String handleException(Exception e) {
        String msg = "处理参数时异常";
        if (e instanceof BindException) {
            FieldError fieldError = ((BindException) e).getFieldError();
            if (fieldError != null) {
                msg = fieldError.getDefaultMessage();
            }
        }
        return msg;
    }

    @Override
    @PostConstruct
    public void register() {
        ExceptionHandlingContext.register(BindException.class, this);
    }
}
