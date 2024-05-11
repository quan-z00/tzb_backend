package com.tzb.backend.pms.domain.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户分页数据
 */
@Data
@Accessors(chain = true)
public class UserPageDto {
    private Long id;
    private String username;
    private Boolean enable;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer gender;
    private String avatar;
    private String address;
    private String email;
    private List<RoleDto> roles;
}
