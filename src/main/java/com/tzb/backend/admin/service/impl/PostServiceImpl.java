package com.tzb.backend.admin.service.impl;

import com.tzb.backend.admin.domain.dto.PostPageDto;
import com.tzb.backend.admin.domain.entity.Post;
import com.tzb.backend.admin.domain.entity.Profile;
import com.tzb.backend.admin.domain.request.PostPageRequest;
import com.tzb.backend.admin.mapper.PostMapper;
import com.tzb.backend.admin.repository.PostRepository;
import com.tzb.backend.admin.repository.ProfileRepository;
import com.tzb.backend.admin.repository.spc.PostSpecifications;
import com.tzb.backend.admin.service.PostService;
import com.tzb.backend.common.core.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 29002
 * @since 2024/5/16
 */
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ProfileRepository profileRepository;
    private final PostSpecifications postSpecifications;
    private final PostMapper postMapper;


    @Override
    public void create(Post post) {

    }

    @Override
    public void update(Post post) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Post getById(Integer id) {
        return null;
    }

    @Override
    public PageResponse getPage(PostPageRequest request) {
        Pageable pageable = request.toPageable();
        List<Integer> likeAuthorIds = profileRepository.findAllByNicknameIsLike("%" + request.getNickname() + "%")
                .stream()
                .map(Profile::getUserId)
                .toList();
        request.setUserIds(likeAuthorIds);
        Specification<Post> spe = postSpecifications.search(request);
        List<PostPageDto> postPageDtos = postRepository.findAll(spe, pageable).getContent()
                .stream()
                .map(postMapper::toDto)
                .toList();
        return new PageResponse(postPageDtos, postRepository.count(spe));
    }
}
