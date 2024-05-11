package com.tzb.backend.pms.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户与角色关系表
 *
 * @author dhb
 */
@Data
@Entity
@Table(name = "user_roles_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "roleId")
    private Long roleId;

}
