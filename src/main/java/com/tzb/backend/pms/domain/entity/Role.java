package com.tzb.backend.pms.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

}
