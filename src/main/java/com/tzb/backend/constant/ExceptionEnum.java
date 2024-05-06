package com.tzb.backend.constant;

import com.tzb.backend.exception.GlobalExceptionHandler;
import lombok.Getter;

/**
 * 定义了系统中可能抛出的异常枚举。
 * 每个枚举常量包含了异常消息和对应的错误代码。
 *
 * 示例使用：
 * <pre>
 *     {@code
 *     throw new CustomException(ExceptionEnum.USER_NOT_EXIST);
 *     }
 * </pre>
 *
 * @see com.tzb.backend.core.CustomException 自定义异常类，用于抛出系统中可能出现的异常。
 * @see GlobalExceptionHandler 全局异常处理器，用于捕获和处理系统中抛出的异常。
 */
@Getter
public enum ExceptionEnum {
    USER_NOT_EXIST("用户不存在", 10001),
    USER_EXIST("用户存在", 10002),
    USER_PASSWORD_ERROR("密码错误", 10003),
    USER_NOT_LOGIN("用户未登录", 10004),
    ;

    private final String msg;
    private final int code;

    ExceptionEnum(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
}
