package com.tzb.backend.admin.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @JsonIgnore
    private User user;

    @Column
    private String avatar;

    private String signature;

    private String nickname;

    private Boolean gender = null;

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
}
