package com.tzb.backend.common.exception.valid;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author 29002
 */
public class ExceptionHandlingContext {
    private static Map<Class<?>, ExceptionHandlingStrategy> strategies = new ConcurrentHashMap<>();

    public static void register(Class<?> clazz, ExceptionHandlingStrategy strategy) {
        strategies.put(clazz, strategy);
    }

    public static String handleException(Exception e) {
        return strategies.get(e.getClass()).handleException(e);
    }
}
