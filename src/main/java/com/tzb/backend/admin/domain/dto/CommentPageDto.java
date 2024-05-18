package com.tzb.backend.admin.domain.dto;

import lombok.Data;

/**
 * @author 29002
 * @since 2024/5/16
 */
@Data
public class CommentPageDto {
    private Integer id;
    private Integer fatherId;
    private Integer postId;
    private String nickname;
    private Integer replyId;
    private String content;
    private Integer status;
}
