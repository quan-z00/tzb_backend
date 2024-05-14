package com.tzb.backend.pms.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.NumberWithFormat;
import com.tzb.backend.common.annotation.ResultWrapper;
import com.tzb.backend.common.auth.RoleType;
import com.tzb.backend.common.auth.Roles;
import com.tzb.backend.common.auth.SaTokenConfigure;
import com.tzb.backend.common.core.CustomException;
import com.tzb.backend.pms.domain.request.*;
import com.tzb.backend.pms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户Controller
 *
 * @author dhb
 */
@ResultWrapper
@CrossOrigin
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    /**
     * 新建用户
     *
     * @return R
     */
    @PostMapping
    @Roles({RoleType.SUPER_ADMIN})
    public Object create(@RequestBody @Validated RegisterUserRequest request) {
        userService.register(request);
        return null;
    }


    /**
     * 获取所有
     *
     * @return R
     */
    @GetMapping
    public Object findAll(UserPageRequest request) {
        return userService.queryPage(request);
    }


    /**
     * 根据id删除
     *
     * @return R
     */
    @DeleteMapping("{id}")
    @Roles({RoleType.SUPER_ADMIN})
    public Object remove(@PathVariable Long id) {
        NumberWithFormat userIdFormat = (NumberWithFormat) StpUtil.getExtra(SaTokenConfigure.JWT_USER_ID_KEY);
        if (userIdFormat.longValue() == id) {
            throw new CustomException("非法操作，不能删除自己！", 11006);
        }
        userService.removeUser(id);
        return null;
    }


    /**
     * 根据id更新
     *
     * @return R
     */
    @PatchMapping("{id}")
    public Object update(@PathVariable Long id, @RequestBody AddUserRolesRequest request) {
        userService.addRoles(id, request);
        return null;
    }


    /**
     * 更新资料
     *
     * @param id id
     * @return R
     */
    @PatchMapping("/profile/{id}")
    public Object updateProfile(@PathVariable Long id, @RequestBody UpdateProfileRequest request) {
        NumberWithFormat userIdFormat = (NumberWithFormat) StpUtil.getExtra(SaTokenConfigure.JWT_USER_ID_KEY);
        if (userIdFormat.longValue() != id) {
            throw new CustomException("越权操作，用户资料只能本人修改！", 11004);
        }
        userService.updateProfile(id, request);
        return null;
    }


    /**
     * 用户信息
     * {@link UserService#detail(Long, String)}
     *
     * @return R
     */
    @GetMapping("/detail")
    public Object detail() {
        NumberWithFormat userId =
                (NumberWithFormat) StpUtil.getExtra(SaTokenConfigure.JWT_USER_ID_KEY);
        String roleCode = (String) StpUtil.getExtra(SaTokenConfigure.JWT_CURRENT_ROLE_KEY);
        return userService.detail(userId.longValue(), roleCode);
    }


    /**
     * 根据用户名获取
     *
     * @param username 用户名
     * @return R
     */
    @GetMapping("/{username}")
    @Roles({RoleType.SUPER_ADMIN})
    public Object findByUsername(@PathVariable String username) {
        throw new CustomException("接口未实现", 99999);
    }


    /**
     * 查询用户的profile
     *
     * @return R
     */
    @GetMapping("/profile/{userId}")
    public Object getUserProfile(@PathVariable Long userId) {
        throw new CustomException("接口未实现", 99999);
    }

    /**
     * 添加角色
     *
     * @param userId 用户id
     * @return R
     */
    @PostMapping("/roles/add/{userId}")
    public Object addRoles(@PathVariable Long userId, @RequestBody @Validated AddUserRolesRequest request) {
        userService.addRoles(userId, request);
        return null;
    }

    /**
     * 重置密码
     *
     * @return R
     */
    @PatchMapping("password/reset/{userId}")
    @Roles({RoleType.SUPER_ADMIN})
    public Object resetPassword(@PathVariable Long userId, @RequestBody @Validated UpdatePasswordRequest request) {
        userService.resetPassword(userId, request);
        return null;
    }
}
