package com.tzb.backend.admin.domain.dto;

import lombok.Data;

/**
 * @author 29002
 * @since 2024/5/16
 */
@Data
public class NewsPageDto {
    private Integer id;
    private String title;
    private String author;
    private Long createdAt;
    private String imageUrl;
    private Integer status;
    private String content;
}
