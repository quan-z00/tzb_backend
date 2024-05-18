package com.tzb.backend.admin.service;

import com.tzb.backend.admin.domain.entity.Post;
import com.tzb.backend.admin.domain.request.PostPageRequest;
import com.tzb.backend.common.core.PageResponse;

/**
 * @author 29002
 * @since 2024/5/16
 */
public interface PostService {
    void create(Post post);

    void update(Post post);

    void delete(Integer id);

    Post getById(Integer id);

    PageResponse getPage(PostPageRequest request);
}
