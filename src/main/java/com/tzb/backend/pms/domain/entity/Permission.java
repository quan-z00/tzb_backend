package com.tzb.backend.pms.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 权限
 *
 * @author dhb
 */
@Getter
@Setter
@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private String type;

    @Column(name = "parentId")
    private Long parentId;

    private String path;

    private String redirect;

    private String icon;

    private String component;

    private String layout;

    @Column(name = "keepAlive")
    private Boolean keepAlive;

    private String method;

    private String description;

    @Column(name = "`show`")
    private Boolean show;

    private Boolean enable;

    @Column(name = "`order`")
    private Integer order;

}
