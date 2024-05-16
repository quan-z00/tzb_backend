package com.tzb.backend.web.service.impl;

import com.tzb.backend.web.domain.entity.DIYshareComment;
import com.tzb.backend.web.repository.DIYshareCommentRepository;
import com.tzb.backend.web.service.DIYshareCommentService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DIYshareCommentServiceImpl implements DIYshareCommentService {


    private final DIYshareCommentRepository diYshareCommentRepository;


    @Autowired
    public DIYshareCommentServiceImpl(DIYshareCommentRepository diYshareCommentRepository) {
        this.diYshareCommentRepository = diYshareCommentRepository;
    }

    @Override
    public Page<DIYshareComment> findAll(Pageable pageable) {
        return diYshareCommentRepository.findAll(pageable);
    }

    @Override
    public List<DIYshareComment> findByTopic(String topic) {
        return diYshareCommentRepository.findByTopic(topic);
    }

    @Override
    public DIYshareComment addComment(DIYshareComment comment) {
        return diYshareCommentRepository.save(comment);
    }

    @Override
    public void delComment(Integer id) {
        diYshareCommentRepository.deleteById(id);
    }

    @Override
    public void updateComment(Integer id, DIYshareComment commentDetails) throws NotFoundException {
//        DIYshareComment comment = diYshareCommentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment not found for this id :: " + id));


        Optional<DIYshareComment> commentOp = diYshareCommentRepository.findById(id);
        if (commentOp.isEmpty()) return;
        DIYshareComment comment = commentOp.get();

        comment.setTopic(commentDetails.getTopic());
        comment.setContent(commentDetails.getContent());
        comment.setSendTime(commentDetails.getSendTime());

        diYshareCommentRepository.save(comment);
    }
}
