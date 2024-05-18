package com.tzb.backend.admin.repository;

import com.tzb.backend.admin.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 29002
 * @since 2024/5/14
 */
@Repository("fUserRepository")
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    boolean existsByUsername(String username);

    User findUserByUsernameAndPassword(String username, String password);

    User findByUsername(String username);


    User findUserById(Integer id);

    int countAllBy();
}
