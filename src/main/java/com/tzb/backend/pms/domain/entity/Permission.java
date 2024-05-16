package com.tzb.backend.pms.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(code, that.code) && Objects.equals(type, that.type) && Objects.equals(parentId, that.parentId) && Objects.equals(path, that.path) && Objects.equals(redirect, that.redirect) && Objects.equals(icon, that.icon) && Objects.equals(component, that.component) && Objects.equals(layout, that.layout) && Objects.equals(keepAlive, that.keepAlive) && Objects.equals(method, that.method) && Objects.equals(description, that.description) && Objects.equals(show, that.show) && Objects.equals(enable, that.enable) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, type, parentId, path, redirect, icon, component, layout, keepAlive, method, description, show, enable, order);
    }
}
