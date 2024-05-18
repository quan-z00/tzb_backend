package com.tzb.backend.admin.domain.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author 29002
 */
@Entity(name = "fProfile")
@Getter
@Setter
@Table(name = "front_user_profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    @JsonIgnore
    private User user;

    @Transient
    private Integer userId;

    @Column
    private String avatar;

    private String signature;

    private String nickname;

    private Boolean gender;

    private LocalDate birthday;

    @Column
    private String location;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Profile() {
        this.nickname = "用户" + (int) (Math.random() * 10000);
    }

    @PostLoad
    private void onLoad() {
        if (user != null) {
            this.userId = user.getId();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id) && Objects.equals(avatar, profile.avatar) && Objects.equals(signature, profile.signature) && Objects.equals(nickname, profile.nickname) && Objects.equals(gender, profile.gender) && Objects.equals(birthday, profile.birthday) && Objects.equals(location, profile.location) && Objects.equals(createdAt, profile.createdAt) && Objects.equals(updatedAt, profile.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, avatar, signature, nickname, gender, birthday, location, createdAt, updatedAt);
    }
}
