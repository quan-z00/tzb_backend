package com.tzb.backend.admin.domain.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 29002
 * @since 2024/5/14
 */
@Data
public class UserPageDto {
    private Integer id;
    private String username;
    private Integer score;
    private Boolean enable;
    private Integer type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDate birthday;
    private String email;
    private Boolean gender;
    private String nickname;
}
