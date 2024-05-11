package com.tzb.backend.pms.repository;


import com.tzb.backend.pms.domain.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * UserRoleMapper
 *
 * @author dhb
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    void deleteAllByRoleId(Long roleId);
    List<UserRole> findAllByRoleId(Long roleId);
    List<UserRole> findAllByUserId(Long userId);
    void deleteByRoleIdAndUserIdIn(Long roleId, List<Long> userIds);
    void deleteByUserId(Long userId);
}
