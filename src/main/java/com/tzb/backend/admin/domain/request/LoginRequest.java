package com.tzb.backend.admin.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author 29002
 * @since 2024/5/14
 */
@Data
public class LoginRequest {
    private String username;
    private String email;
    @NotNull(message = "密码不能为空")
    private String password;
}
