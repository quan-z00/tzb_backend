package com.tzb.backend.admin.repository;

import com.tzb.backend.admin.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 29002
 * @since 2024/5/14
 */
public interface PostRepository extends JpaRepository<Post, Integer> {
}
