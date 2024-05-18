package com.tzb.backend.admin.mapper;

import com.tzb.backend.admin.domain.dto.PostPageDto;
import com.tzb.backend.admin.domain.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.tzb.backend.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

/**
 * @author 29002
 * @since 2024/5/17
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface PostMapper {
    @Mapping(target = "authorName", source = "author.profile.nickname")
    @Mapping(target = "authorId", source = "author.id")
    PostPageDto toDto(Post post);
}
