package com.tzb.backend.exception.valid;

public interface ExceptionHandlingStrategy {
    String handleException(Exception e);
    void register();
}
