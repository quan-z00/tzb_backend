package com.tzb.backend.admin.controller;

import com.tzb.backend.admin.domain.request.PostPageRequest;
import com.tzb.backend.admin.service.PostService;
import com.tzb.backend.common.annotation.ResultWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 29002
 * @since 2024/5/16
 */
@ResultWrapper
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
@CrossOrigin
public class PostController {
    private final PostService postService;

    @GetMapping
    public Object getPage(PostPageRequest request) {
        return postService.getPage(request);
    }
}
