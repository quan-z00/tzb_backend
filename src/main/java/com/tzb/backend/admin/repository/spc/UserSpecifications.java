package com.tzb.backend.admin.repository.spc;

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
        return searchUsers(username, email, null, null);
    }

    public Specification<User> searchUsers(String username, String email, Boolean enable, Integer type) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(username)) {
                predicates.add(criteriaBuilder.equal(root.get("username"), username));
            }
            if (StringUtils.hasText(email)) {
                predicates.add(criteriaBuilder.equal(root.get("email"), email));
            }
            if (enable != null) {
                predicates.add(criteriaBuilder.equal(root.get("enable"), enable));
            }
            if (type != null) {
                predicates.add(criteriaBuilder.equal(root.get("type"), type));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


    public Specification<User> searchRequestUsers(String username, String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.equal(root.get("username"), username),
                criteriaBuilder.equal(root.get("email"), email));
    }
}
