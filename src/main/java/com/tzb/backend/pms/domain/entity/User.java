package com.tzb.backend.pms.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 用户
 *
 * @author dhb
 */
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private boolean enable;

    @Column(name = "createTime")
    private LocalDateTime createTime;

    @Column(name = "updateTime")
    private LocalDateTime updateTime;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    public User() {
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }
}
