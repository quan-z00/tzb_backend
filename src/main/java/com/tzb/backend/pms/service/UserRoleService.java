package com.tzb.backend.pms.service;


import com.tzb.backend.pms.domain.entity.UserRole;

import java.util.List;

/**
 * UserRoleService
 *
 * @author dhb
 */
public interface UserRoleService {
    void removeByRoleId(Long roleId);
    List<UserRole> findAllByRoleId(Long roleId);
    void saveBatch(List<UserRole> userRoleList);
    void removeUserRolesByRoleIdAndUserIds(Long roleId, List<Long> userIds);
}
