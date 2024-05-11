package com.tzb.backend.pms.mapper;

import com.tzb.backend.pms.domain.dto.PermissionDto;
import com.tzb.backend.pms.domain.entity.Permission;
import com.tzb.backend.pms.domain.request.CreatePermissionRequest;
import com.tzb.backend.pms.domain.request.UpdatePermissionRequest;
import org.mapstruct.Mapper;

import static com.tzb.backend.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface PermissionMapper {
    PermissionDto toPermissionDto(Permission permission);
    Permission toPermission(UpdatePermissionRequest request);
    Permission toPermission(CreatePermissionRequest request);
}
