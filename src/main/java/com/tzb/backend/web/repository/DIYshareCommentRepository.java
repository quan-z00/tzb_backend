package com.tzb.backend.web.repository;

import com.tzb.backend.web.domain.entity.DIYshareComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DIYshareCommentRepository extends JpaRepository<DIYshareComment,Integer> {
    Page<DIYshareComment> findAll(Pageable pageable);
    List<DIYshareComment> findByTopic(String topic);

}
