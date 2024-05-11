package com.tzb.backend.pms.mapper;

import com.tzb.backend.pms.domain.dto.RoleDto;
import com.tzb.backend.pms.domain.dto.RolePageDto;
import com.tzb.backend.pms.domain.entity.Role;
import com.tzb.backend.pms.domain.request.CreateRoleRequest;
import org.mapstruct.Mapper;

import static com.tzb.backend.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface RoleMapper {
    RoleDto toRoleDto(Role role);
    RolePageDto toRolePageDto(Role role);
    Role toRole(CreateRoleRequest createRoleRequest);
}
