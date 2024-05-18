package com.tzb.backend.admin.repository.spc;

import com.tzb.backend.admin.domain.entity.News;
import com.tzb.backend.admin.domain.request.NewsPageRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 29002
 * @since 2024/5/16
 */
@Component
public class NewsSpecifications {

    public Specification<News> search(NewsPageRequest request) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getTitle() != null && !request.getTitle().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + request.getTitle() + "%"));
            }
            if (request.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), request.getStatus()));
            }
            if (request.getAuthor() != null && !request.getAuthor().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("author"), "%" + request.getAuthor() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
