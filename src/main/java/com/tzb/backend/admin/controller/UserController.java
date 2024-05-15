package com.tzb.backend.admin.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.tzb.backend.admin.domain.request.*;
import com.tzb.backend.admin.service.UserService;
import com.tzb.backend.common.annotation.ResultWrapper;
import com.tzb.backend.common.auth.RoleType;
import com.tzb.backend.common.auth.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 29002
 * @since 2024/5/14
 */
@ResultWrapper
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/user")
@RestController("fUserController")
public class UserController {
    private final UserService userService;

    @GetMapping
    public Object getUsers(UserPageRequest request) {
        return userService.getPage(request);
    }

    //切换状态
    @Roles({RoleType.SUPER_ADMIN})
    @PatchMapping("/update/status/{id}")
    public Object updateUserStatus(@PathVariable("id") Integer userId, @RequestBody UpdateUserStatusRequest request) {
        userService.updateUserStatus(userId, request);
        return null;
    }

    @PostMapping("/update")
    public Object updateUser(@RequestBody @Validated UpdateUserProfileRequest request) {
        userService.updateUser(request);
        return null;
    }

    @SaIgnore
    @PostMapping("/login")
    public Object login(@RequestBody @Validated LoginRequest request) {
        return userService.login(request);
    }

    @SaIgnore
    @PostMapping("/register")
    public Object register(@RequestBody @Validated RegisterRequest request) {
        return userService.register(request);
    }


}
