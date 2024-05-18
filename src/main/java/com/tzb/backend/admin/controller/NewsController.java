package com.tzb.backend.admin.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.tzb.backend.admin.domain.request.AddNewsRequest;
import com.tzb.backend.admin.domain.request.DeleteNewsRequest;
import com.tzb.backend.admin.domain.request.NewsPageRequest;
import com.tzb.backend.admin.domain.request.UpdateNewsRequest;
import com.tzb.backend.admin.service.NewsService;
import com.tzb.backend.common.annotation.ResultWrapper;
import com.tzb.backend.common.auth.RoleType;
import com.tzb.backend.common.auth.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 29002
 * @since 2024/5/17
 */
@RestController
@CrossOrigin
@ResultWrapper
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;


    @GetMapping
    public Object getPage(NewsPageRequest request) {
        return newsService.getPage(request);
    }

    @SaIgnore
    @GetMapping("/hot")
    public Object getHot() {
        return newsService.getHot();
    }

    @Roles({RoleType.SUPER_ADMIN})
    @DeleteMapping
    public Object delete(@Validated DeleteNewsRequest request) {
        newsService.delete(request);
        return null;
    }

    @Roles({RoleType.SUPER_ADMIN})
    @PutMapping
    public Object update(@RequestBody @Validated UpdateNewsRequest request) {
        newsService.update(request);
        return null;
    }

    @Roles({RoleType.SUPER_ADMIN})
    @PostMapping
    public Object add(@RequestBody @Validated AddNewsRequest request) {
        newsService.add(request);
        return null;
    }


}
