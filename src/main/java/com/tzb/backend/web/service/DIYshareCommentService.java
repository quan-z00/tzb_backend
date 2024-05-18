package com.tzb.backend.web.service;

import com.tzb.backend.web.domain.dto.DIYshareCommentDTO;
import com.tzb.backend.web.domain.entity.DIYshareComment;
import com.tzb.backend.web.repository.DIYshareCommentRepository;
import com.tzb.backend.web.specification.CommentSpecificationDTO;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DIYshareCommentService {


    Page<DIYshareCommentDTO> findAll(Pageable pageable);

    public Page<DIYshareCommentDTO> searchAndFindAll(CommentSpecificationDTO commentSpecificationDTO, Pageable pageable);

    List<DIYshareComment> findByTopic(String topic);

    DIYshareComment addComment(DIYshareComment comment);

    void delComment(Integer id);

    void updateComment(Integer id, DIYshareComment commentDetails) throws NotFoundException;
}
