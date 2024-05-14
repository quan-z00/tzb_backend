package com.tzb.backend.admin.repository;

import com.tzb.backend.admin.domain.entity.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 29002
 */
@Component
public class UserSpecifications {

    public Specification<User> searchUsers(String username, String email) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(username)) {
                predicates.add(criteriaBuilder.equal(root.get("username"), username));
            }
            if (StringUtils.hasText(email)) {
                predicates.add(criteriaBuilder.equal(root.get("email"), email));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
