package com.tzb.backend.pms.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.core.lang.Pair;
import com.tzb.backend.common.annotation.ResultWrapper;
import com.tzb.backend.common.auth.SaTokenConfigure;
import com.tzb.backend.pms.domain.request.ChangePasswordRequest;
import com.tzb.backend.pms.domain.request.LoginRequest;
import com.tzb.backend.pms.domain.request.RegisterUserRequest;
import com.tzb.backend.pms.service.CaptchaService;
import com.tzb.backend.pms.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 鉴权相关的Controller.
 *
 * @author dhb
 */
@CrossOrigin
@ResultWrapper
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    /**
     * user service.
     */
    private final UserService userService;

    private final CaptchaService captchaService;

    private static final String CAPTCHA_KEY = "captchaKey";

    /**
     * 用户登录接口.
     *
     * @param request 请求
     * @return 返回token
     */
    @PostMapping("/login")
    public Object login(@RequestBody final LoginRequest request,
                        HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        String captchaKey = (String) session.getAttribute(CAPTCHA_KEY);
        if (captchaKey != null) {
            request.setCaptchaKey(captchaKey);
        }
        return userService.login(request);
    }

    /**
     * 注册
     *
     */
    @PostMapping("/register")
    public Object register(@RequestBody RegisterUserRequest request) {
        userService.register(request);
        return null;
    }

    /**
     * 刷新token
     *
     * @return 新的token
     */
    @GetMapping("/refresh/token")
    public Object refreshToken() {
        return userService.refreshToken();
    }


    /**
     * 切换角色
     *
     * @param roleCode 角色代码
     */
    @PostMapping("/current-role/switch/{roleCode}")
    public Object switchRole(@PathVariable String roleCode) {
        NumberWithFormat userId =
                (NumberWithFormat) StpUtil.getExtra(SaTokenConfigure.JWT_USER_ID_KEY);
        return userService.switchRole(userId.longValue(), roleCode);
    }

    /**
     * 登出，当使用无状态的jwt时，注销登录不需要任何操作
     *
     */
    @PostMapping("/logout")
    public Object logout() {
        StpUtil.logout();
        return null;
    }

    /**
     * 图形验证码
     */
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Pair<String, ICaptcha> captchaPair = captchaService.create();
        HttpSession session = request.getSession();
        session.setAttribute(CAPTCHA_KEY, captchaPair.getKey());
        captchaPair.getValue().write(response.getOutputStream());
    }


    /**
     * 修改密码
     *
     */
    @PostMapping("/password")
    public Object changePassword(@RequestBody ChangePasswordRequest request) {
        userService.changePassword(request);
        return null;
    }

}
