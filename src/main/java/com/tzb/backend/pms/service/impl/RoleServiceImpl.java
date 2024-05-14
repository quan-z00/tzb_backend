package com.tzb.backend.pms.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tzb.backend.common.auth.PmsConstant;
import com.tzb.backend.common.auth.RoleType;
import com.tzb.backend.common.constant.ExceptionEnum;
import com.tzb.backend.common.core.CustomException;
import com.tzb.backend.pms.domain.dto.PermissionDto;
import com.tzb.backend.pms.domain.dto.RolePageDto;
import com.tzb.backend.pms.domain.entity.Permission;
import com.tzb.backend.pms.domain.entity.Role;
import com.tzb.backend.pms.domain.entity.RolePermission;
import com.tzb.backend.pms.domain.entity.UserRole;
import com.tzb.backend.pms.domain.request.*;
import com.tzb.backend.pms.mapper.RoleMapper;
import com.tzb.backend.pms.repository.RoleRepository;
import com.tzb.backend.pms.repository.UserRoleRepository;
import com.tzb.backend.pms.service.PermissionService;
import com.tzb.backend.pms.service.RolePermissionService;
import com.tzb.backend.pms.service.RoleService;
import com.tzb.backend.pms.service.UserRoleService;
import com.tzb.backend.pms.util.PermissionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * RoleServiceImpl
 *
 * @author dhb
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final PermissionService permissionService;
    private final RolePermissionService rolePermissionService;
    private final UserRoleService userRoleService;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final UserRoleRepository userRoleRepository;

    @Override
    public List<Role> findRolesByUserId(Long userId) {
        List<UserRole> userRoles = userRoleRepository.findAllByUserId(userId);
        return userRoles.stream().map(userRole -> roleRepository.getRoleById(userRole.getRoleId())).toList();
    }

    @Override
    public List<Tree<Long>> findRolePermissionsTree(String roleCode) {
        Role role = this.findByCode(roleCode);
        if (role == null) {
            throw new CustomException(ExceptionEnum.ROLE_CURRENT_NOT_EXIST);
        }
        List<Permission> permissions =
                PmsConstant.ROLE_ADMIN.equals(roleCode) ? permissionService.findAll()
                        : permissionService.findByRoleId(role.getId());

        return PermissionUtil.toTreeNode(permissions, null);
    }

    @Override
    public Role findByCode(String roleCode) {
        return roleRepository.findByCode(roleCode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRole(CreateRoleRequest request) {
        boolean exists = roleRepository.existsByCodeOrName(request.getCode(), request.getName());

        if (exists) {
            throw new CustomException(ExceptionEnum.ROLE_EXIST);
        }

        Role role = roleMapper.toRole(request);
        roleRepository.save(role);
        List<RolePermission> permissionList = request.getPermissionIds().stream()
                .map(permId -> {
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRoleId(role.getId());
                    rolePermission.setPermissionId(permId);
                    return rolePermission;
                }).toList();
        rolePermissionService.saveBatch(permissionList);
    }

    @Override
    public List<RolePageDto> queryPage(RolePageRequest request) {
        Pageable pageable = request.toPageable();

        Page<Role> pageResult = roleRepository.findAll(pageable);

        return pageResult.map(role -> {
            RolePageDto dto = roleMapper.toRolePageDto(role);

            // 获取角色权限列表并设置到DTO中
            List<Long> permissionList = rolePermissionService.findAllByRoleId(role.getId())
                    .stream().map(RolePermission::getPermissionId)
                    .toList();
            dto.setPermissionIds(permissionList);

            return dto;
        }).toList();
    }

    @Override
    public List<PermissionDto> findRolePermissions(Long id) {
        return permissionService.findByRoleId(id)
                .stream()
                .map(permission -> permission.convert(PermissionDto.class))
                .toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(Long id, UpdateRoleRequest request) {
        Role role = roleRepository.getRoleById(id);
        if (role == null) {
            throw new CustomException(ExceptionEnum.ROLE_NOT_EXIST);
        }
        if (RoleType.SUPER_ADMIN.equals(role.getCode())) {
            throw new CustomException(ExceptionEnum.ROLE_SUPER_ADMIN_REVISE);
        }
        if (StrUtil.isNotBlank(request.getName())) {
            role.setName(request.getName());
        }
        if (ObjectUtil.isNotNull(request.getEnable())) {
            role.setEnable(request.getEnable());
        }
        roleRepository.save(role);
        if (request.getPermissionIds() != null) {
            rolePermissionService.removeByRoleId(id);
            if (!request.getPermissionIds().isEmpty()) {
                List<RolePermission> permissionList = request.getPermissionIds().stream()
                        .map(permId -> {
                            RolePermission rolePermission = new RolePermission();
                            rolePermission.setRoleId(id);
                            rolePermission.setPermissionId(permId);
                            return rolePermission;
                        }).toList();
                rolePermissionService.saveBatch(permissionList);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeRole(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role == null) {
            throw new CustomException(ExceptionEnum.ROLE_NOT_EXIST);
        }
        if (RoleType.SUPER_ADMIN.equals(role.getCode())) {
            throw new CustomException(ExceptionEnum.ROLE_SUPER_ADMIN_REVISE);
        }
        roleRepository.deleteAllById(id);
        userRoleService.removeByRoleId(id);
        rolePermissionService.removeByRoleId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRolePermissions(AddRolePermissionsRequest request) {
        Role role = roleRepository.getRoleById(request.getId());
        if (role == null) {
            throw new CustomException(ExceptionEnum.ROLE_NOT_EXIST);
        }
        if (RoleType.SUPER_ADMIN.equals(role.getCode())) {
            throw new CustomException(ExceptionEnum.ROLE_SUPER_ADMIN_ADD_PERMISSION);
        }
        List<Long> list = rolePermissionService.getPermissionIdsByRoleId(role.getId());

        CollUtil.removeWithAddIf(request.getPermissionIds(), list::contains);
        List<RolePermission> permissionList = request.getPermissionIds()
                .stream()
                .map(permId -> {
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRoleId(role.getId());
                    rolePermission.setPermissionId(permId);
                    return rolePermission;
                }).toList();
        rolePermissionService.saveBatch(permissionList);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRoleUsers(Long roleId, AddRoleUsersRequest request) {
        boolean exists = roleRepository.existsRoleById(roleId);
        if (!exists) {
            throw new CustomException(ExceptionEnum.ROLE_NOT_EXIST);
        }
        List<Long> list = userRoleService.findAllByRoleId(roleId)
                .stream()
                .map(UserRole::getUserId).toList();

        CollUtil.removeWithAddIf(request.getUserIds(), list::contains);
        List<UserRole> permissionList = request.getUserIds()
                .stream()
                .map(userId -> {
                    UserRole userRole = new UserRole();
                    userRole.setRoleId(roleId);
                    userRole.setUserId(userId);
                    return userRole;
                }).toList();
        userRoleService.saveBatch(permissionList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeRoleUsers(Long roleId, RemoveRoleUsersRequest request) {
        boolean exists = roleRepository.existsRoleById(roleId);
        if (!exists) {
            throw new CustomException(ExceptionEnum.ROLE_NOT_EXIST);
        }
        userRoleService.removeUserRolesByRoleIdAndUserIds(roleId, request.getUserIds());
    }

    @Override
    public List<Role> findByEnable(Boolean enable) {
        return roleRepository.findAllByEnable(enable);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAllBy();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.getRoleById(id);
    }

}
