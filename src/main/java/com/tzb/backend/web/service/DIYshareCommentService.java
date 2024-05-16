package com.tzb.backend.web.service;

import com.tzb.backend.web.domain.entity.DIYshareComment;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DIYshareCommentService {

    Page<DIYshareComment> findAll(Pageable pageable);
    List<DIYshareComment> findByTopic(String topic);

    DIYshareComment addComment(DIYshareComment comment);

    void delComment(Integer id);

    void updateComment(Integer id, DIYshareComment commentDetails) throws NotFoundException;
}
