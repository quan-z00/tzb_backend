package com.tzb.backend.web.mapper;


import com.tzb.backend.web.domain.dto.DIYshareCommentDTO;
import com.tzb.backend.web.domain.entity.DIYshareComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.tzb.backend.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;


@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface DIYshareCommentMapper {
    DIYshareCommentDTO toDTO(DIYshareComment diYshareComment);


    @Mapping(target = "nickname", source = "profile.nickname")
    @Mapping(target = "gender", source = "profile.gender")
    @Mapping(target = "avatar", source = "profile.avatar")
    @Mapping(target = "address", source = "profile.address")
    @Mapping(target = "email", source = "profile.email")
    DIYshareCommentDTO toDIYshareCommentDTO(DIYshareComment diYshareComment);

}
