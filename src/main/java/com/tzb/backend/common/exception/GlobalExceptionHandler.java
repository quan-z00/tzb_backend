package com.tzb.backend.common.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.exception.NotSafeException;
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

    @ExceptionHandler(NotLoginException.class)
    public ResultWrapper handlerNotLoginException(NotLoginException nle) {
        // 不同异常返回不同状态码
        String message = "";
        if (nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供Token";
        } else if (nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "未提供有效的Token";
        } else if (nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "登录信息已过期，请重新登录";
        } else if (nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "您的账户已在另一台设备上登录，如非本人操作，请立即修改密码";
        } else if (nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "已被系统强制下线";
        } else {
            message = "当前会话未登录";
        }
        // 返回给前端
        return ResultUtil.fail(401, message);
    }

    @ExceptionHandler
    public ResultWrapper handlerNotRoleException(NotRoleException e) {
        return ResultUtil.fail(403, "无此角色：" + e.getRole());
    }

    @ExceptionHandler
    public ResultWrapper handlerNotPermissionException(NotPermissionException e) {
        return ResultUtil.fail(403, "无此权限：" + e.getCode());
    }

//    @ExceptionHandler
//    public ResultWrapper handlerDisableLoginException(DisableLoginException e) {
//        return  ResultUtil.fail(401, "账户被封禁：" + e.getDisableTime() + "秒后解封");
//    }

    @ExceptionHandler
    public ResultWrapper handlerNotSafeException(NotSafeException e) {
        return ResultUtil.fail(401, "二级认证异常：" + e.getMessage());
    }
}
