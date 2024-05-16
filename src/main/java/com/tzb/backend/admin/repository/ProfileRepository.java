package com.tzb.backend.admin.repository;

import com.tzb.backend.admin.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 29002
 * @since 2024/5/14
 */
@Repository("fProfileRepository")
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile findByUserId(Integer userId);
}
