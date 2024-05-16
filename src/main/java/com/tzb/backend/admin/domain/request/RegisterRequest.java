package com.tzb.backend.admin.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author 29002
 * @since 2024/5/14
 */
@Data
public class RegisterRequest {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 20, message = "用户名长度必须在3到20之间")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,20}$", message = "用户名只能包含字母、数字、下划线、横杠")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6到20之间")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,20}$", message = "密码必须包含大小写字母和数字")
    private String password;
    private String repeatPassword;
    @NotBlank
    @Email
    private String email;
}
