package com.tzb.backend.admin.domain.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author 29002
 * @since 2024/5/16
 */
@Entity
public class Comments {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "father_id")
    private Integer fatherId;

    @Column(name = "post_id")
    private int postId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "reply_id")
    private Integer replyId;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private int status;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments = (Comments) o;
        return id == comments.id && postId == comments.postId && userId == comments.userId && status == comments.status && Objects.equals(fatherId, comments.fatherId) && Objects.equals(replyId, comments.replyId) && Objects.equals(content, comments.content) && Objects.equals(createdAt, comments.createdAt) && Objects.equals(updatedAt, comments.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fatherId, postId, userId, replyId, content, status, createdAt, updatedAt);
    }
}
