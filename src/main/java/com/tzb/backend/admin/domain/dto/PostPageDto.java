package com.tzb.backend.admin.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 29002
 * @since 2024/5/16
 */
@Data
public class PostPageDto {
    private Integer id;
    private String title;
    private String content;
    private String tags;
    // 1: 已发布 2:已删除 3:审核中 4:审核失败
    private Integer status;
    private int views = 0;
    private int likes = 0;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer authorId;
    private String authorName;
}
