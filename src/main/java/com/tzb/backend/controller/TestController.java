package com.tzb.backend.controller;

import com.tzb.backend.annotation.ExcludeResultWrapper;
import com.tzb.backend.annotation.ResultWrapper;
import com.tzb.backend.core.CustomException;
import com.tzb.backend.repository.SpecialExhibitRepository;
import com.tzb.backend.repository.UserRepository;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author 29002
 */
@ResultWrapper
@RestController
@RequestMapping("/api/test")
public class TestController {

    private final StringRedisTemplate template;
    private final UserRepository userRepository;
    private final SpecialExhibitRepository specialExhibitRepository;

    public TestController(StringRedisTemplate template, UserRepository userRepository, SpecialExhibitRepository specialExhibitRepository) {
        this.template = template;
        this.userRepository = userRepository;
        this.specialExhibitRepository = specialExhibitRepository;
    }

    @GetMapping("/redis")
    public Object redisTest() {
        //测试redis
        template.opsForValue().set("test", "test");
        return template.opsForValue().get("test");
    }

    @GetMapping("/user")
    public Object validatedTest() {
        return userRepository.findByUsername("admin");
    }

    @ExcludeResultWrapper
    @GetMapping("/hello")
    public Object hello() {
        return "hello world";
    }

    @GetMapping("/customException")
    public Object customException() {
        throw new CustomException("自定义异常", 3000);
    }

    @GetMapping("/test1")
    public Object test1() {
        return specialExhibitRepository.findAllBy();
    }
}
