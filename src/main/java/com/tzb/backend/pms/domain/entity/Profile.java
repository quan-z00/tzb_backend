package com.tzb.backend.pms.domain.entity;

import cn.dhbin.mapstruct.helper.core.Convert;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 用户信息
 *
 * @author dhb
 */
//https://img0.baidu.com/it/u=3059338894,2334870186&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500
@Data
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

}
