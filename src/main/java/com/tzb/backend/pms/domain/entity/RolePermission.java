package com.tzb.backend.pms.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色与权限关系
 *
 * @author dhb
 */
@Getter
@Setter
@Entity(name = "role_permissions_permission")
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "roleId")
    private Long roleId;

    @Column(name = "permissionId")
    private Long permissionId;

}
