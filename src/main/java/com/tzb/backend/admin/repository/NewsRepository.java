package com.tzb.backend.admin.repository;

import com.tzb.backend.admin.domain.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @author 29002
 * @since 2024/5/17
 */
public interface NewsRepository extends JpaRepository<News, Integer>, JpaSpecificationExecutor<News> {
    void deleteById(Integer id);
}
