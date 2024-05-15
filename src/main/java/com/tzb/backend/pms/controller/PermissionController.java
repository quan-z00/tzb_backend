package com.tzb.backend.pms.controller;


import com.tzb.backend.common.annotation.ResultWrapper;
import com.tzb.backend.pms.domain.entity.Permission;
import com.tzb.backend.pms.domain.request.CreatePermissionRequest;
import com.tzb.backend.pms.domain.request.UpdatePermissionRequest;
import com.tzb.backend.pms.mapper.PermissionMapper;
import com.tzb.backend.pms.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限Controller
 *
 * @author dhb
 */
@ResultWrapper
@CrossOrigin
@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class PermissionController {


    private final PermissionService permissionService;
    private final PermissionMapper permissionMapper;

    /**
     * 新建权限
     */
    @PostMapping
    public Object create(@RequestBody @Validated CreatePermissionRequest request) {
        permissionService.create(request);
        return null;
    }


    /**
     * 批量创建权限
     */
    @PostMapping("/batch")
    public Object batchCreate(@RequestBody @Validated List<CreatePermissionRequest> request) {
        permissionService.createBatch(request);
        return null;
    }

    /**
     * 获取所有权限
     */
    @GetMapping
    public Object findAll() {
        return permissionService.findAllMenu();
    }

    /**
     * 获取所有权限树
     */
    @GetMapping("/tree")
    public Object findAllTree() {
        return permissionService.findAllMenuTree();
    }

    /**
     * 获取菜单树
     */
    @GetMapping("menu/tree")
    public Object findMenuTree() {
        return permissionService.findAllMenuTree();
    }

    /**
     * 根据id获取
     */
    @GetMapping("{id}")
    public Object findOne(@PathVariable Long id) {
        return permissionMapper.toPermissionDto(permissionService.getById(id));
    }

    /**
     * 根据id更新
     *
     * @return R
     */
    @PatchMapping("{id}")
    public Object update(@PathVariable Long id, @RequestBody UpdatePermissionRequest request) {
        Permission permission =permissionMapper.toPermission(request);
        permission.setId(id);
        permissionService.updateById(permission);
        return null;
    }

    /**
     * 根据id删除
     *
     * @return R
     */
    @DeleteMapping("{id}")
    public Object remove(@PathVariable Long id) {
        permissionService.removeById(id);
      return null;
    }


    /**
     * 获取
     *
     * @return R
     */
    @GetMapping("/button/{parentId}")
    public Object findButtonAndApi(@PathVariable Long parentId) {
        return permissionService.findButton(parentId)
                .stream().map(permissionMapper::toPermissionDto)
                .toList();
    }

    /**
     * 校验 path 存不存在menu资源内
     *
     * @return R
     */
    @GetMapping("/menu/validate")
    public Object validateMenuPath(String path) {
        return permissionService.validateMenuPath(path);
    }

}
