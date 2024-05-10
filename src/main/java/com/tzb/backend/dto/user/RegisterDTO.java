package com.tzb.backend.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 29002
 */
@Setter
@Getter
public class RegisterDTO {

    @NotNull(message = "用户名不能为空")
    @Pattern(regexp = "[0-9a-zA-Z]{6,}", message = "用户名格式错误")
    private String username;

    @NotNull(message = "密码不能为空")
    @Pattern(regexp = "[0-9a-zA-Z]{6,}", message = "密码格式错误")
    private String password;

    @Email
    private String email;

    @NotNull(message = "验证码不能为空")
    @Pattern(regexp = "[0-9]{6,}", message = "验证码格式错误")
    private String captcha;
}
