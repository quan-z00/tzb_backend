package com.tzb.backend.exception.valid;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Component
public class MethodArgumentNotValidExceptionStrategy implements ExceptionHandlingStrategy {
    @Override
    public String handleException(Exception e) {
        String msg = "处理参数时异常";
        if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                msg = fieldError.getDefaultMessage();
            }
        }
        return msg;
    }

    @Override
    @PostConstruct
    public void register() {
        ExceptionHandlingContext.register(MethodArgumentNotValidException.class, this);
    }
}




