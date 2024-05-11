package com.tzb.backend.pms.domain.request;

import lombok.Data;

import java.util.List;

/**
 * 给用户分配角色
 *
 * @author dhb
 */
@Data
public class AddUserRolesRequest {

    private List<Long> roleIds;

    private Boolean enable;
}
