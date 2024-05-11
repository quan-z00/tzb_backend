package com.tzb.backend.pms.mapper;

import com.tzb.backend.pms.domain.dto.ProfileDto;
import com.tzb.backend.pms.domain.entity.Profile;
import com.tzb.backend.pms.domain.request.RegisterUserProfileRequest;
import com.tzb.backend.pms.domain.request.UpdateProfileRequest;
import org.mapstruct.Mapper;

import static com.tzb.backend.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface ProfileMapper {
    public Profile toProfile(ProfileDto profileDto);
    public ProfileDto toProfileDto(Profile profile);
    public Profile toProfile(UpdateProfileRequest updateProfileRequest);
    public Profile toProfile(RegisterUserProfileRequest registerUserProfileRequest);
}
