package com.tzb.backend.admin.domain.dto;

import com.tzb.backend.admin.domain.entity.Profile;
import com.tzb.backend.admin.enums.UserLevel;
import com.tzb.backend.admin.enums.UserStatus;
import lombok.Data;

/**
 * @author 29002
 * @since 2024/5/14
 */
@Data
public class UserPageDto {
    private Integer id;
    private String username;
    private Integer score;
    private UserStatus status;
    private UserLevel role;
    private Profile profile;
}
