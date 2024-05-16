package com.tzb.backend.admin.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.tzb.backend.admin.domain.dto.UserPageDto;
import com.tzb.backend.admin.domain.entity.Profile;
import com.tzb.backend.admin.domain.entity.User;
import com.tzb.backend.admin.domain.request.*;
import com.tzb.backend.admin.mapper.FProfileMapper;
import com.tzb.backend.admin.mapper.FUserMapper;
import com.tzb.backend.admin.repository.ProfileRepository;
import com.tzb.backend.admin.repository.UserRepository;
import com.tzb.backend.admin.repository.UserSpecifications;
import com.tzb.backend.admin.service.UserService;
import com.tzb.backend.common.constant.ExceptionEnum;
import com.tzb.backend.common.core.CustomException;
import com.tzb.backend.common.core.PageResponse;
import com.tzb.backend.common.utils.CopyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 29002
 * @since 2024/5/14
 */
@Service("fUserServiceImpl")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final FProfileMapper profileMapper;
    private final FUserMapper userMapper;
    private final UserRepository userRepository;
    private final UserSpecifications userSpecifications;
    private final ProfileRepository profileRepository;

    @Override
    public PageResponse getPage(UserPageRequest userPageRequest) {
        Pageable pageable = userPageRequest.toPageable();

        String username = userPageRequest.getUsername();
        String email = userPageRequest.getEmail();
        Boolean enable = userPageRequest.getEnable();
        Integer type = userPageRequest.getType();

        Specification<User> spec = userSpecifications.searchUsers(username, email, enable, type);

        Page<User> userPage = userRepository.findAll(spec, pageable);
        List<UserPageDto> userPageDtoList = userPage.getContent().stream().map(userMapper::toUserPageDto).toList();
        long total = userRepository.count(spec);

        return new PageResponse(userPageDtoList, total);
    }

    @Override
    public void updateUser(UpdateUserProfileRequest updateUserProfileRequest) {
        Profile dbProfile = profileRepository.findByUserId(updateUserProfileRequest.getUserId());
        Profile profile = profileMapper.toProfile(updateUserProfileRequest);
        CopyUtils.copyProperties(profile, dbProfile);
        profileRepository.save(dbProfile);
    }

    @Override
    public String login(LoginRequest request) {

        Specification<User> specification = userSpecifications.searchUsers(request.getUsername(), request.getEmail());
        List<User> dbUserList = userRepository.findAll(specification);
        if (dbUserList.isEmpty()) {
            throw new CustomException(ExceptionEnum.USER_NOT_EXIST);
        }
        User dbUser = dbUserList.get(0);
        if (!dbUser.getPassword().equals(SaSecureUtil.sha256(request.getPassword()))) {
            throw new CustomException(ExceptionEnum.USER_USERNAME_OR_PASSWORD_ERROR);
        }

        if (!dbUser.getEnable()) {
            throw new CustomException(ExceptionEnum.USER_BANED);
        }
        StpUtil.login(dbUser.getUsername());
        return StpUtil.getTokenValue();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String register(RegisterRequest request) {
        Specification<User> specification = userSpecifications.searchRequestUsers(request.getUsername(), request.getEmail());
        List<User> dbUserList = userRepository.findAll(specification);
        if (!dbUserList.isEmpty()) {
            throw new CustomException(ExceptionEnum.USER_EXIST);
        }
        request.setPassword(SaSecureUtil.sha256(request.getPassword()));
        User user = userMapper.toUser(request);
        User saved = userRepository.save(user);
        Profile profile = new Profile();
        profile.setUser(saved);
        profileRepository.save(profile);
        StpUtil.login(saved.getUsername());
        return StpUtil.getTokenValue();
    }

    @Override
    public void updateUserStatus(Integer userId, UpdateUserStatusRequest request) {
        User dbUser = userRepository.findUserById(userId);
        User user = userMapper.toUser(request);
        CopyUtils.copyProperties(user, dbUser);
        userRepository.save(dbUser);
    }
}
