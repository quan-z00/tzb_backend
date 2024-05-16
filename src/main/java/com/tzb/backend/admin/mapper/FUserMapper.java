package com.tzb.backend.admin.mapper;

import com.tzb.backend.admin.domain.dto.UserPageDto;
import com.tzb.backend.admin.domain.entity.User;
import com.tzb.backend.admin.domain.request.LoginRequest;
import com.tzb.backend.admin.domain.request.RegisterRequest;
import com.tzb.backend.admin.domain.request.UpdateUserStatusRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.tzb.backend.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

/**
 * @author 29002
 * @since 2024/5/14
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface FUserMapper {
    @Mapping(target = "gender", source = "profile.gender")
    @Mapping(target = "birthday", source = "profile.birthday")
    @Mapping(target = "nickname", source = "profile.nickname")
    UserPageDto toUserPageDto(User user);

    User toUser(RegisterRequest registerRequest);

    User toUser(LoginRequest loginRequest);

    User toUser(UpdateUserStatusRequest request);
}
