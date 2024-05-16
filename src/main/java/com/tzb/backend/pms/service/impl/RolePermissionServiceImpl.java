package com.tzb.backend.pms.service.impl;


import com.tzb.backend.pms.domain.entity.RolePermission;
import com.tzb.backend.pms.repository.RolePermissionRepository;
import com.tzb.backend.pms.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RolePermissionServiceImpl
 *
 * @author dhb
 */
@Service
@RequiredArgsConstructor
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;
    @Override
    public void saveBatch(List<RolePermission> permissionList) {
        rolePermissionRepository.saveAll(permissionList);
    }

    @Override
    public List<RolePermission> findAllByRoleId(Long roleId) {
        return rolePermissionRepository.findAllByRoleId(roleId);
    }

    @Override
    public void removeByRoleId(Long roleId) {
        rolePermissionRepository.deleteAllByRoleId(roleId);
    }

    @Override
    public List<Long> getPermissionIdsByRoleId(Long roleId) {
        return rolePermissionRepository.findPermissionIdsByRoleId(roleId);
    }
}
