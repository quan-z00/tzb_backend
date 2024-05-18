package com.tzb.backend.admin.repository;

import com.tzb.backend.admin.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author 29002
 * @since 2024/5/16
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> getCommentsByPostId(Integer postId);
}
