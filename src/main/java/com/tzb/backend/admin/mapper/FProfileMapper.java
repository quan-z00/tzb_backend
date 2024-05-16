package com.tzb.backend.admin.mapper;

import com.tzb.backend.admin.domain.entity.Profile;
import com.tzb.backend.admin.domain.request.UpdateUserProfileRequest;
import org.mapstruct.Mapper;

import static com.tzb.backend.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

/**
 * @author 29002
 * @since 2024/5/14
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface FProfileMapper {
    Profile toProfile(UpdateUserProfileRequest updateUserProfileRequest);
}
