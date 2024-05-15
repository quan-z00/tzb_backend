package com.tzb.backend.admin.domain.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 29002
 * @since 2024/5/14
 */
@Data
public class UpdateUserProfileRequest {
    private Integer userId;
    private String nickname;
    private String signature;
    private String location;
    private String avatar;
    private Boolean gender;
    private LocalDate birthday;
    private LocalDateTime updatedAt;

    public UpdateUserProfileRequest() {
        this.updatedAt = LocalDateTime.now();
    }

}
