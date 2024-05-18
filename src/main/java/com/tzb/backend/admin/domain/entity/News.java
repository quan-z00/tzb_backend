package com.tzb.backend.admin.domain.entity;

import com.tzb.backend.common.converter.LocalDateTimeLongConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author 29002
 * @since 2024/5/17
 */
@Getter
@Setter
@Entity
@Table(name = "tb_news")
public class News {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "author")
    private String author;
    @Basic
    @Convert(converter = LocalDateTimeLongConverter.class)
    @Column(name = "created_at")
    private Long createdAt;
    @Basic
    @Column(name = "image_url")
    private String imageUrl;
    @Basic
    @Column(name = "status")
    private Integer status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id) && Objects.equals(title, news.title) && Objects.equals(content, news.content) && Objects.equals(author, news.author) && Objects.equals(createdAt, news.createdAt) && Objects.equals(imageUrl, news.imageUrl) && Objects.equals(status, news.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, author, createdAt, imageUrl, status);
    }
}
