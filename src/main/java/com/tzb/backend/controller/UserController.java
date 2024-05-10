package com.tzb.backend.controller;

import com.tzb.backend.annotation.ResultWrapper;
import com.tzb.backend.domain.User;
import com.tzb.backend.dto.user.LoginDTO;
import com.tzb.backend.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 29002
 */
@CrossOrigin
@RestController
@ResultWrapper
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Object login(@Validated @RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @PostMapping("/register")
    //TODO LoginDTO
    public Object register(@RequestBody User user) {
        return userService.register(user);
    }
}
