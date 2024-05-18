package com.tzb.backend.admin.service.impl;

import com.tzb.backend.admin.domain.dto.NewsPageDto;
import com.tzb.backend.admin.domain.entity.News;
import com.tzb.backend.admin.domain.request.AddNewsRequest;
import com.tzb.backend.admin.domain.request.DeleteNewsRequest;
import com.tzb.backend.admin.domain.request.NewsPageRequest;
import com.tzb.backend.admin.domain.request.UpdateNewsRequest;
import com.tzb.backend.admin.mapper.NewsMapper;
import com.tzb.backend.admin.repository.NewsRepository;
import com.tzb.backend.admin.repository.spc.NewsSpecifications;
import com.tzb.backend.admin.service.NewsService;
import com.tzb.backend.common.core.CustomException;
import com.tzb.backend.common.core.PageResponse;
import com.tzb.backend.common.utils.CopyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 29002
 * @since 2024/5/17
 */
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final NewsSpecifications newsSpecifications;

    @Override
    public PageResponse getPage(NewsPageRequest request) {
        Pageable pageable = request.toPageable(Sort.by(Sort.Direction.DESC, "createdAt"));

        Specification<News> spe = newsSpecifications.search(request);

        List<NewsPageDto> list = newsRepository.findAll(spe, pageable).getContent().stream()
                .map(newsMapper::toPageDto)
                .toList();

        return new PageResponse(list, newsRepository.count(spe));
    }

    @Override
    public List<NewsPageDto> getHot() {
        Pageable pageable = PageRequest.of(0, 6, Sort.by(Sort.Direction.DESC, "createdAt"));
        return newsRepository.findAll(pageable).getContent().stream()
                .map(newsMapper::toPageDto)
                .toList();
    }

    @Override
    public void update(UpdateNewsRequest request) {
        System.out.println(request);
        News dbNews = newsRepository.findById(request.getId()).orElseThrow(() -> new CustomException("新闻不存在", 404));
        News news = newsMapper.toNews(request);
        CopyUtils.copyProperties(news, dbNews);
        newsRepository.save(dbNews);
    }

    @Override
    @Transactional
    public void delete(DeleteNewsRequest request) {
        newsRepository.deleteById(request.getId());
    }

    @Override
    public void add(AddNewsRequest request) {
        newsRepository.save(newsMapper.toNews(request));
    }
}
