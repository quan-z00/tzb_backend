package com.tzb.backend.admin.service;

import com.tzb.backend.admin.domain.dto.NewsPageDto;
import com.tzb.backend.admin.domain.request.AddNewsRequest;
import com.tzb.backend.admin.domain.request.DeleteNewsRequest;
import com.tzb.backend.admin.domain.request.NewsPageRequest;
import com.tzb.backend.admin.domain.request.UpdateNewsRequest;
import com.tzb.backend.common.core.PageResponse;

import java.util.List;

/**
 * @author 29002
 * @since 2024/5/17
 */
public interface NewsService {
    PageResponse getPage(NewsPageRequest request);

    List<NewsPageDto> getHot();

    void update(UpdateNewsRequest request);

    void delete(DeleteNewsRequest request);

    void add(AddNewsRequest request);
}
