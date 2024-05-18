package com.tzb.backend.pms.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * 角色
 *
 * @author dhb
 */
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    private Boolean enable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(code, role.code) && Objects.equals(name, role.name) && Objects.equals(enable, role.enable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, enable);
    }
}
