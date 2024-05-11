package com.tzb.backend.pms.mapper;

import com.tzb.backend.pms.domain.dto.UserDetailDto;
import com.tzb.backend.pms.domain.dto.UserPageDto;
import com.tzb.backend.pms.domain.entity.User;
import com.tzb.backend.pms.domain.request.RegisterUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import static com.tzb.backend.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface UserMapper {
    UserDetailDto toUserDetailDto(User user);
    UserPageDto toUserPageDto(User user);
    User toUser(RegisterUserRequest registerUserRequest);
}
