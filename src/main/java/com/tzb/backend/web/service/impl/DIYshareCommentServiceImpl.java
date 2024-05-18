package com.tzb.backend.web.service.impl;

import com.tzb.backend.web.domain.dto.DIYshareCommentDTO;
import com.tzb.backend.web.domain.entity.DIYshareComment;
import com.tzb.backend.web.mapper.DIYshareCommentMapper;
import com.tzb.backend.web.repository.DIYshareCommentRepository;
import com.tzb.backend.web.service.DIYshareCommentService;
import com.tzb.backend.web.specification.CommentSpecificationDTO;
import com.tzb.backend.web.specification.DIYshareCommentSpecification;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DIYshareCommentServiceImpl implements DIYshareCommentService {


    private DIYshareCommentRepository diYshareCommentRepository;
    private DIYshareCommentMapper diYshareCommentMapper;

    @Override
    public Page<DIYshareCommentDTO> findAll(Pageable pageable) {
        return diYshareCommentRepository.findAll(pageable).map(diYshareCommentMapper::toDIYshareCommentDTO);
    }

    @Override
    public Page<DIYshareCommentDTO> searchAndFindAll(CommentSpecificationDTO commentSpecificationDTO, Pageable pageable) {
        if (commentSpecificationDTO.isEmpty()) {
            // 如果所有搜索条件都为空，则返回普通分页结果
            return diYshareCommentRepository.findAll(pageable)
                    .map(diYshareCommentMapper::toDIYshareCommentDTO);
        } else {
            // 如果有搜索条件，则构建Specification进行查询
            Specification<DIYshareCommentDTO> spec = buildSpecification(searchCriteria);
            return diYshareCommentRepository.findAll(spec, pageable)
                    .map(diYshareCommentMapper::toDIYshareCommentDTO);
        }
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

        Optional<DIYshareComment> commentOp = diYshareCommentRepository.findById(id);
        if (commentOp.isEmpty()) return;
        DIYshareComment comment = commentOp.get();

        comment.setTopic(commentDetails.getTopic());
        comment.setContent(commentDetails.getContent());
        comment.setSendTime(commentDetails.getSendTime());

        diYshareCommentRepository.save(comment);
    }
}
