package com.tzb.backend.admin.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author 29002
 * @since 2024/5/16
 */
@Entity
@Setter
@Getter
@Table(name = "comments")
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "father_id")
    private Integer fatherId;

    @Column(name = "post_id")
    private int postId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Profile user;


    @Column(name = "reply_id")
    private Integer replyId;

    @Column(name = "content")
    private String content;

    //1:正常 2:已删除 3:审核中 4:审核失败
    @Column(name = "status")
    private int status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && postId == comment.postId && status == comment.status && Objects.equals(fatherId, comment.fatherId) && Objects.equals(user, comment.user) && Objects.equals(replyId, comment.replyId) && Objects.equals(content, comment.content) && Objects.equals(createdAt, comment.createdAt) && Objects.equals(updatedAt, comment.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fatherId, postId, user, replyId, content, status, createdAt, updatedAt);
    }
}
