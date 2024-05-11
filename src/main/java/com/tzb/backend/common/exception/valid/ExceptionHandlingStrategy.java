package com.tzb.backend.common.exception.valid;

public interface ExceptionHandlingStrategy {
    String handleException(Exception e);
    void register();
}
