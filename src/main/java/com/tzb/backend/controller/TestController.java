package com.tzb.backend.controller;

import com.tzb.backend.annotation.ExcludeResultWrapper;
import com.tzb.backend.annotation.ResultWrapper;
import com.tzb.backend.constant.ExceptionEnum;
import com.tzb.backend.core.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 29002
 */
@ResultWrapper
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/exception")
    public Object exceptionTest() {
        throw new CustomException(ExceptionEnum.USER_NOT_LOGIN);
    }

    @GetMapping("/hello")
    public Object hello() {
        return "hello world";
    }
}
