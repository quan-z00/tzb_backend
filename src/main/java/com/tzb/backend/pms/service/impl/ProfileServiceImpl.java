package com.tzb.backend.pms.service.impl;


import com.tzb.backend.pms.domain.entity.Profile;
import com.tzb.backend.pms.repository.ProfileRepository;
import com.tzb.backend.pms.service.ProfileService;
import org.springframework.stereotype.Service;

/**
 * ProfileServiceImpl
 *
 * @author dhb
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile findByUserId(Long userId) {
        return profileRepository.findByUserId(userId);
    }

}
