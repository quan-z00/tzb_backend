package com.tzb.backend.admin.mapper;

import com.tzb.backend.admin.domain.dto.NewsPageDto;
import com.tzb.backend.admin.domain.entity.News;
import com.tzb.backend.admin.domain.request.AddNewsRequest;
import com.tzb.backend.admin.domain.request.UpdateNewsRequest;
import org.mapstruct.Mapper;

import static com.tzb.backend.common.mapstruct.MapstructConstant.DEFAULT_COMPONENT_MODEL;

/**
 * @author 29002
 * @since 2024/5/17
 */
@Mapper(componentModel = DEFAULT_COMPONENT_MODEL)
public interface NewsMapper {
    News toNews(UpdateNewsRequest request);

    News toNews(AddNewsRequest request);

    NewsPageDto toPageDto(News news);
}
