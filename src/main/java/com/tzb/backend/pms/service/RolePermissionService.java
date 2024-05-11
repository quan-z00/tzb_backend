package com.tzb.backend.pms.service;


import com.tzb.backend.pms.domain.entity.RolePermission;

import java.util.List;

/**
 * RolePermissionService
 *
 * @author dhb
 */
public interface RolePermissionService {
    void saveBatch(List<RolePermission> permissionList);
    List<RolePermission> findAllByRoleId(Long roleId);
    void removeByRoleId(Long roleId);
    List<Long> getPermissionIdsByRoleId(Long roleId);
}
