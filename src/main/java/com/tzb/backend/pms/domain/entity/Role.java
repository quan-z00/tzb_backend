package com.tzb.backend.pms.domain.entity;

import cn.dhbin.mapstruct.helper.core.Convert;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色
 *
 * @author dhb
 */
@Data
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
