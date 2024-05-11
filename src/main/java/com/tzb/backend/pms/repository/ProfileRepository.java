package com.tzb.backend.pms.repository;


import com.tzb.backend.pms.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户信息Mapper
 *
 * @author dhb
 */
public interface ProfileRepository extends JpaRepository<Profile,Long> {
    Profile findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
