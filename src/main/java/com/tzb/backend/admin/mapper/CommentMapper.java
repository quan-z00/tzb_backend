package com.tzb.backend.admin.mapper;

import com.tzb.backend.admin.domain.dto.CommentPageDto;
import com.tzb.backend.admin.domain.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.tzb.backend.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

/**
 * @author 29002
 * @since 2024/5/16
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface CommentMapper {
    @Mapping(source = "user.nickname", target = "nickname")
    CommentPageDto toDto(Comment comment);
}
