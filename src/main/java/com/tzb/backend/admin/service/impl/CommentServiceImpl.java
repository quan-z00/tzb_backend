package com.tzb.backend.admin.service.impl;

import com.tzb.backend.admin.domain.entity.Comment;
import com.tzb.backend.admin.repository.CommentRepository;
import com.tzb.backend.admin.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 29002
 * @since 2024/5/16
 */
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public List<Comment> getByPostId(Integer postId) {
        return commentRepository.getCommentsByPostId(postId);
    }
}
