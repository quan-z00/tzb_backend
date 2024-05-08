package com.tzb.backend.exception.valid;

import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

@Component
public class ConstraintViolationExceptionStrategy implements ExceptionHandlingStrategy {
    @Override
    public String handleException(Exception e) {
        String msg = "处理参数时异常";
        if (e instanceof ConstraintViolationException) {
            msg = e.getMessage();
            if (msg != null) {
                int lastIndex = msg.lastIndexOf(':');
                if (lastIndex >= 0) {
                    msg = msg.substring(lastIndex + 1).trim();
                }
            }
        }
        return msg;
    }

    @Override
    @PostConstruct
    public void register() {
        ExceptionHandlingContext.register(ConstraintViolationException.class,this);
    }
}
