package com.tzb.backend.pms.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.tzb.backend.common.annotation.ResultWrapper;
import com.tzb.backend.common.auth.RoleType;
import com.tzb.backend.common.auth.Roles;
import com.tzb.backend.common.auth.SaTokenConfigure;
import com.tzb.backend.pms.domain.entity.Role;
import com.tzb.backend.pms.domain.request.*;
import com.tzb.backend.pms.mapper.RoleMapper;
import com.tzb.backend.pms.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色Controller
 */
@ResultWrapper
@CrossOrigin
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final RoleMapper roleMapper;

    /**
     * 新建角色
     *
     * @return R
     */
    @PostMapping
    @Roles(RoleType.SUPER_ADMIN)
    public Object create(@RequestBody @Validated CreateRoleRequest request) {
        roleService.createRole(request);
        return null;
    }

    /**
     * 获取所有角色
     *
     * @return R
     */
    @GetMapping
    public Object findAll(
            @RequestParam(value = "enable", required = false) Boolean enable
    ) {
        List<Role> roles = enable != null ? roleService.findByEnable(enable) : roleService.findAll();

        return roles.stream()
                .map(roleMapper::toRoleDto)
                .collect(Collectors.toList());
    }

    /**
     * 分页
     *
     * @return R
     */
    @GetMapping("/page")
    public Object findPagination(RolePageRequest request) {
        return roleService.queryPage(request);
    }

    /**
     * 查询角色权限
     *
     * @return R
     */
    @GetMapping("/permissions")
    public Object findRolePermissions(Long id) {
        return roleService.findRolePermissions(id);
    }


    /**
     * 根据id获取
     *
     * @return R
     */
    @GetMapping("{id}")
    @Roles(RoleType.SUPER_ADMIN)
    public Object findOne(@PathVariable Long id) {
        return roleMapper.toRoleDto(roleService.findById(id));
    }


    /**
     * 根据id更新
     *
     * @return R
     */
    @PatchMapping("{id}")
    @Roles({RoleType.SUPER_ADMIN, RoleType.SYS_ADMIN, RoleType.ROLE_PMS})
    public Object update(@PathVariable Long id, @RequestBody UpdateRoleRequest request) {
        roleService.updateRole(id, request);
        return null;
    }


    /**
     * 根据id删除
     *
     * @return R
     */
    @DeleteMapping("{id}")
    @Roles({RoleType.SUPER_ADMIN})
    public Object remove(@PathVariable Long id) {
        roleService.removeRole(id);
        return null;
    }


    /**
     * 给角色添加权限
     *
     * @return R
     */
    @PostMapping("/permissions/add")
    @Roles({RoleType.SUPER_ADMIN})
    public Object addRolePermissions(@RequestBody @Validated AddRolePermissionsRequest request) {
        roleService.addRolePermissions(request);
        return null;
    }

    /**
     * 角色的权限树
     *
     * @return R
     */
    @GetMapping("/permissions/tree")
    public Object permissionTree() {
        String roleCode = (String) StpUtil.getExtra(SaTokenConfigure.JWT_CURRENT_ROLE_KEY);
        return roleService.findRolePermissionsTree(roleCode);
    }


    /**
     * 给角色分配用户
     *
     * @return R
     */
    @PatchMapping("/users/add/{roleId}")
    @Roles({RoleType.SUPER_ADMIN})
    public Object addRoleUsers(@PathVariable Long roleId, @RequestBody AddRoleUsersRequest request) {
        roleService.addRoleUsers(roleId, request);
        return null;
    }


    @PatchMapping("/users/remove/{roleId}")
    @Roles({RoleType.SUPER_ADMIN})
    public Object removeRoleUsers(@PathVariable Long roleId, @RequestBody RemoveRoleUsersRequest request) {
        roleService.removeRoleUsers(roleId, request);
        return null;
    }

}
