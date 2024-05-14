package com.tzb.backend.common.constant;

import com.tzb.backend.common.core.CustomException;
import com.tzb.backend.common.exception.GlobalExceptionHandler;
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
 * @author 29002
 * @see CustomException 自定义异常类，用于抛出系统中可能出现的异常。
 * @see GlobalExceptionHandler 全局异常处理器，用于捕获和处理系统中抛出的异常。
 */
@Getter
public enum ExceptionEnum {
    USER_NOT_EXIST("用户不存在", 10001),
    USER_EXIST("用户存在", 10002),
    USER_USERNAME_OR_PASSWORD_ERROR("用户名或密码错误", 10002),
    USER_PASSWORD_ERROR("密码错误", 10002),
    USER_NOT_LOGIN("用户未登录", 10004),
    USER_BANED("用户被禁用", 10005),

    //以上为通用异常(1xxxx)

    CAPTCHA_ERROR("验证码错误", 10003),
    CAPTCHA_FAILED("密码验证失败", 10004),

    ROLE_CURRENT_NOT_EXIST("当前角色不存在或者已删除", 40000),
    ROLE_NOT_EXIST("角色不存在或者已删除", 40001),
    ROLE_EXIST("角色已存在（角色名和角色编码不能重复）", 40002),
    ROLE_SUPER_ADMIN_REVISE("不允许修改超级管理员",40003),
    ROLE_SUPER_ADMIN_ADD_PERMISSION("无需给超级管理员授权",40004),
    ROLE_NOT_PERMISSION("无权限，请联系管理员申请权限", 40005),
    ROLE_NOT_ROLE("越权操作", 40006),
    ROLE_NOT_ROLE_USER("您目前暂无此角色或已被禁用，请联系管理员", 40007),
    NOT_DELETE_ROOT_ROLE("不能删除根角色", 40008),
    //以上为后台角色权限相关异常(1xxxx,4xxxx)
    //以上为前台用户相关异常(2xxxx)
    ILLEGAL_OPERATION("非法操作", 9999),
    ;

    private final String msg;
    private final int code;

    ExceptionEnum(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

}
