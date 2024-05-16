package com.tzb.backend.pms.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * 用户信息
 *
 * @author dhb
 */
//https://img0.baidu.com/it/u=3059338894,2334870186&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500
@Getter
@Setter
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer gender;

    private String avatar;

    private String address;

    private String email;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "nickName")
    private String nickName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId",insertable=false, updatable=false)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id) && Objects.equals(gender, profile.gender) && Objects.equals(avatar, profile.avatar) && Objects.equals(address, profile.address) && Objects.equals(email, profile.email) && Objects.equals(userId, profile.userId) && Objects.equals(nickName, profile.nickName) && Objects.equals(user, profile.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gender, avatar, address, email, userId, nickName, user);
    }
}
