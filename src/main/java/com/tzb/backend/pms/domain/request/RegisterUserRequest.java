package com.tzb.backend.pms.domain.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 注册用户
 *
 * @author dhb
 */
@Data
public class RegisterUserRequest  {

    @Length(min = 6, max = 20, message = "用户名长度必须是6到20之间")
    private String username;

    @Length(min = 6, max = 20, message = "密码长度必须是6到20之间")
    private String password;

    private Boolean enable;

    private RegisterUserProfileRequest profile;

    private List<Long> roleIds;


}
