package com.tzb.backend.pms.domain.dto;

import lombok.Data;

/**
 * 登录响应token
 *
 * @author dhb
 */
@Data
public class LoginTokenDto {

    /**
     * 通过登录获取的token
     */
    private String accessToken;

}
