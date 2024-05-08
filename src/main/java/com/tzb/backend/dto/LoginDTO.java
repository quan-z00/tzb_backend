package com.tzb.backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author 29002
 */
@Data
public class LoginDTO {
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "验证码不能为空")
    @Pattern(regexp = "[0-9]{6,}", message = "验证码格式错误")
    private String captcha;
}
