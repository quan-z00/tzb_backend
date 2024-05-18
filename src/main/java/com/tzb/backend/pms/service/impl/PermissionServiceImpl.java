package com.tzb.backend.pms.service.impl;


import cn.hutool.core.lang.tree.Tree;
import com.tzb.backend.common.utils.CopyUtils;
import com.tzb.backend.pms.domain.dto.PermissionDto;
import com.tzb.backend.pms.domain.entity.Permission;
import com.tzb.backend.pms.domain.entity.RolePermission;
import com.tzb.backend.pms.domain.request.CreatePermissionRequest;
import com.tzb.backend.pms.mapper.PermissionMapper;
import com.tzb.backend.pms.repository.PermissionRepository;
import com.tzb.backend.pms.repository.RolePermissionRepository;
import com.tzb.backend.pms.service.PermissionService;
import com.tzb.backend.pms.util.PermissionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 权限服务类的实现类，主要负责权限相关的处理
 *
 * @author dhb
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private static final String TYPE_MENU = "MENU";
    private static final String TYPE_BUTTON = "BUTTON";
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final PermissionMapper permissionMapper;


    @Override
    public List<Permission> findByRoleId(Long roleId) {
        List<RolePermission> rolePermissions = rolePermissionRepository.findAllByRoleId(roleId);
        return rolePermissions.stream()
                .map(rolePermission -> permissionRepository.findById(rolePermission.getPermissionId()).orElse(null))
                .filter(o -> Objects.nonNull(o) && o.getEnable())
                .toList();
    }

    @Override
    public void create(CreatePermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permissionRepository.save(permission);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createBatch(List<CreatePermissionRequest> request) {
        List<Permission> list = request.stream().map(permissionMapper::toPermission)
                .toList();
        permissionRepository.saveAll(list);
    }

    @Override
    public List<PermissionDto> findAllMenu() {
        return permissionRepository.findAllByType(TYPE_MENU)
                .stream()
                .map(permissionMapper::toPermissionDto)
                .toList();
    }

    @Override
    public List<Tree<Long>> findAllMenuTree() {
        List<Permission> permissions = permissionRepository.findAllByTypeOrderByOrderAsc(TYPE_MENU);
        return PermissionUtil.toTreeNode(permissions, null);
    }

    @Override
    public List<Permission> findButton(Long parentId) {
        return permissionRepository.findAllByParentIdAndTypeInOrderByOrderAsc(parentId, List.of(TYPE_BUTTON));
    }

    @Override
    public boolean validateMenuPath(String path) {
        return permissionRepository.existsByPath(path);
    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission getById(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }

    @Override
    public void updateById(Permission permission) {
        Permission dbPermission = permissionRepository.findAllById(permission.getId());
        CopyUtils.copyProperties(permission, dbPermission);
        permissionRepository.save(dbPermission);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        permissionRepository.deletePermissionById(id);
    }

}
