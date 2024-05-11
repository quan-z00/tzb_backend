package com.tzb.backend.pms.domain.request;

import lombok.Data;

import java.util.List;

/**
 * 更新角色
 *
 * @author dhb
 */
@Data
public class UpdateRoleRequest {

    private String name;

    private Boolean enable;

    private List<Long> permissionIds;


}
