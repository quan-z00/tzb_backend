package com.tzb.backend.pms.service;


import com.tzb.backend.pms.domain.entity.Profile;

/**
 * ProfileService
 *
 * @author dhb
 */
public interface ProfileService  {

    /**
     * 通过用户id获取用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    Profile findByUserId(Long userId);

}
