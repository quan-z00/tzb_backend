package com.tzb.backend.repository;

import com.tzb.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author 29002
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
