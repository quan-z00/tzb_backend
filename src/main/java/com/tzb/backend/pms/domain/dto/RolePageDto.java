package com.tzb.backend.pms.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * 角色Dto
 *
 * @author dhb
 */
@Data
public class RolePageDto {

    private Long id;

    private String code;

    private String name;

    private Boolean enable;

    private List<Long> permissionIds;

}
