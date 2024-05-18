package com.tzb.backend.admin.service;

import com.tzb.backend.admin.domain.entity.Comment;

import java.util.List;

/**
 * @author 29002
 * @since 2024/5/16
 */
public interface CommentService {
    List<Comment> getByPostId(Integer postId);
}
