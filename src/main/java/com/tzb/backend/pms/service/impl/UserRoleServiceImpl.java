package com.tzb.backend.pms.service.impl;


import com.tzb.backend.pms.domain.entity.UserRole;
import com.tzb.backend.pms.repository.UserRoleRepository;
import com.tzb.backend.pms.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserRoleServiceImpl
 *
 * @author dhb
 */
@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;
    @Override
    public void removeByRoleId(Long roleId) {
        userRoleRepository.deleteAllByRoleId(roleId);
    }

    @Override
    public List<UserRole> findAllByRoleId(Long roleId) {
        return userRoleRepository.findAllByRoleId(roleId);
    }

    @Override
    public void saveBatch(List<UserRole> userRoleList) {
        userRoleRepository.saveAll(userRoleList);
    }
    @Override
    public void removeUserRolesByRoleIdAndUserIds(Long roleId, List<Long> userIds) {
        userRoleRepository.deleteByRoleIdAndUserIdIn(roleId, userIds);
    }
}
