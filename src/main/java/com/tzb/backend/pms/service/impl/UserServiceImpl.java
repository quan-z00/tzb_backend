package com.tzb.backend.pms.service.impl;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.alibaba.fastjson2.JSON;
import com.tzb.backend.common.auth.SaTokenConfigure;
import com.tzb.backend.common.constant.ExceptionEnum;
import com.tzb.backend.common.core.CustomException;
import com.tzb.backend.common.utils.CopyUtils;
import com.tzb.backend.pms.domain.dto.*;
import com.tzb.backend.pms.domain.entity.Profile;
import com.tzb.backend.pms.domain.entity.Role;
import com.tzb.backend.pms.domain.entity.User;
import com.tzb.backend.pms.domain.entity.UserRole;
import com.tzb.backend.pms.domain.request.*;
import com.tzb.backend.pms.mapper.ProfileMapper;
import com.tzb.backend.pms.mapper.RoleMapper;
import com.tzb.backend.pms.mapper.UserMapper;
import com.tzb.backend.pms.repository.ProfileRepository;
import com.tzb.backend.pms.repository.RoleRepository;
import com.tzb.backend.pms.repository.UserRoleRepository;
import com.tzb.backend.pms.service.*;
import com.tzb.backend.pms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * User Service impl
 *
 * @author dhb
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final UserRoleRepository userRoleRepository;

    private final RoleService roleService;
    private final ProfileService profileService;
    private final UserRoleService userRoleService;
    private final CaptchaService captchaService;

    private final UserMapper userMapper;
    private final ProfileMapper profileMapper;
    private final RoleMapper roleMapper;


    @Override
    public LoginTokenDto login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            throw new CustomException(ExceptionEnum.USER_USERNAME_OR_PASSWORD_ERROR);
        }
        if (StrUtil.isBlank(request.getCaptchaKey())
                || !captchaService.verify(request.getCaptchaKey(), request.getCaptcha())) {
            throw new CustomException(ExceptionEnum.CAPTCHA_ERROR);
        }
        return login(request, user);
    }

    private LoginTokenDto login(LoginRequest request, User user) {
        boolean checkPw = BCrypt.checkpw(request.getPassword(), user.getPassword());
        if (checkPw) {
            // 查询用户的角色
            List<Role> roles = roleService.findRolesByUserId(user.getId());

            return generateToken(user, roles, roles.isEmpty() ? "" : roles.getFirst().getCode());
        } else {
            throw new CustomException(ExceptionEnum.USER_USERNAME_OR_PASSWORD_ERROR);
        }
    }

    @Override
    public UserDetailDto detail(Long userId, String roleCode) {
        User user = userRepository.findUserById(userId);
        UserDetailDto userDetailDto = userMapper.toUserDetailDto(user);
        ProfileDto profileDto = profileMapper.toProfileDto(profileService.findByUserId(userId));
        List<RoleDto> roleDtoList = roleService.findRolesByUserId(userId)
                .stream()
                .filter(Role::getEnable)
                .map(roleMapper::toRoleDto)
                .toList();
        if (roleDtoList.isEmpty()) {
            throw new CustomException(ExceptionEnum.ROLE_NOT_ROLE_USER);
        }
        userDetailDto.setProfile(profileDto);
        userDetailDto.setRoles(roleDtoList);
        for (RoleDto roleDto : roleDtoList) {
            if (roleDto.getCode().equals(roleCode)) {
                userDetailDto.setCurrentRole(roleDto);
                break;
            }
        }
        return userDetailDto;
    }

    @Override
    public LoginTokenDto switchRole(Long userId, String roleCode) {
        User user = userRepository.findUserById(userId);
        List<Role> roles = roleService.findRolesByUserId(userId);
        Role currentRole = null;
        for (Role role : roles) {
            if (roleCode.equals(role.getCode())) {
                currentRole = role;
            }
        }
        if (currentRole == null) {
            throw new CustomException(ExceptionEnum.ROLE_NOT_ROLE_USER);
        }
        return generateToken(user, roles, currentRole.getCode());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterUserRequest request) {
        boolean exists = userRepository.existsByUsername(request.getUsername());
        if (exists) {
            throw new CustomException(ExceptionEnum.ROLE_EXIST);
        }
        User user = userMapper.toUser(request);
        user.setPassword(BCrypt.hashpw(user.getPassword()));
        userRepository.save(user);

        Profile profile = profileMapper.toProfile(Optional.ofNullable(request.getProfile()).orElse(new RegisterUserProfileRequest()));
        profile.setUserId(user.getId());
        if (CollUtil.isNotEmpty(request.getRoleIds())) {
            List<UserRole> roleList = request.getRoleIds().stream()
                    .map(roleId -> {
                        UserRole userRole = new UserRole();
                        userRole.setUserId(user.getId());
                        userRole.setRoleId(roleId);
                        return userRole;
                    }).toList();
            userRoleService.saveBatch(roleList);
        }
        profileRepository.save(profile);
    }

    @Override
    public LoginTokenDto refreshToken() {
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        StpUtil.login(tokenInfo.getLoginId(), SaLoginConfig
                .setExtra(SaTokenConfigure.JWT_USER_ID_KEY,
                        StpUtil.getExtra(SaTokenConfigure.JWT_USER_ID_KEY))
                .setExtra(SaTokenConfigure.JWT_USERNAME_KEY,
                        StpUtil.getExtra(SaTokenConfigure.JWT_USERNAME_KEY))
                .setExtra(SaTokenConfigure.JWT_CURRENT_ROLE_KEY,
                        StpUtil.getExtra(SaTokenConfigure.JWT_CURRENT_ROLE_KEY))
                .setExtra(SaTokenConfigure.JWT_ROLE_LIST_KEY,
                        StpUtil.getExtra(SaTokenConfigure.JWT_ROLE_LIST_KEY))
        );
        SaTokenInfo newTokenInfo = StpUtil.getTokenInfo();
        LoginTokenDto dto = new LoginTokenDto();
        dto.setAccessToken(newTokenInfo.getTokenValue());
        return dto;
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        String username = (String) StpUtil.getExtra(SaTokenConfigure.JWT_USERNAME_KEY);
        User user = userRepository.findByUsername(username);
        if (!BCrypt.checkpw(request.getOldPassword(), user.getPassword())) {
            throw new CustomException(ExceptionEnum.ROLE_NOT_ROLE);
        }
        user.setPassword(BCrypt.hashpw(request.getNewPassword()));
        userRepository.save(user);
        StpUtil.logout();
    }

    @Override
    public List<UserPageDto> queryPage(UserPageRequest request) {
        Pageable pageable = request.toPageable();

        Page<User> pageResult = userRepository.findAll(pageable);

        return pageResult.map(user -> {
            UserPageDto dto = userMapper.toUserPageDto(user);
            Profile profile = user.getProfile();
            dto
                    .setAddress(profile.getAddress())
                    .setGender(profile.getGender())
                    .setEmail(profile.getEmail())
                    .setAvatar(profile.getAvatar())
            ;
            // 获取用户角色列表并设置到DTO中
            List<RoleDto> roleDtoList = roleService.findRolesByUserId(user.getId()).stream()
                    .map(roleMapper::toRoleDto)
                    .toList();
            dto.setRoles(roleDtoList);
            return dto;
        }).toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeUser(Long id) {
        if (id == 1) {
            throw new CustomException(ExceptionEnum.NOT_DELETE_ROOT_ROLE);
        }
        userRepository.deleteById(id);
        profileRepository.deleteByUserId(id);
    }

    @Override
    public void resetPassword(Long userId, UpdatePasswordRequest request) {
        String newPw = BCrypt.hashpw(request.getPassword());
        User user = userRepository.findUserById(userId);
        user.setPassword(newPw);
        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRoles(Long userId, AddUserRolesRequest request) {
        if (Objects.nonNull(request.getRoleIds())) {
            userRoleRepository.deleteByUserId(userId);
            List<UserRole> list = request.getRoleIds().stream()
                    .map(roleId -> {
                        UserRole userRole = new UserRole();
                        userRole.setUserId(userId);
                        userRole.setRoleId(roleId);
                        return userRole;
                    }).toList();
            userRoleService.saveBatch(list);
        }
        if (Objects.nonNull(request.getEnable())) {
            User user = new User();
            user.setEnable(request.getEnable());
            User dbUser = userRepository.findUserById(userId);
            CopyUtils.copyProperties(user, dbUser);
            userRepository.save(dbUser);
        }

    }

    @Override
    public void updateProfile(Long id, UpdateProfileRequest request) {
        Profile profile = profileMapper.toProfile(request);
        Profile dbProfile = profileRepository.findByUserId(id);
        CopyUtils.copyProperties(profile, dbProfile);
        profileRepository.save(dbProfile);
    }


    private LoginTokenDto generateToken(User user, List<Role> roles, String currentRoleCode) {
        // 密码验证成功
        StpUtil.login(user.getId(),
                SaLoginConfig.setExtra(SaTokenConfigure.JWT_USER_ID_KEY, user.getId())
                        .setExtra(SaTokenConfigure.JWT_USERNAME_KEY, user.getUsername())
                        .setExtra(SaTokenConfigure.JWT_CURRENT_ROLE_KEY, currentRoleCode)
                        .setExtra(SaTokenConfigure.JWT_ROLE_LIST_KEY, roles));
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        LoginTokenDto dto = new LoginTokenDto();
        dto.setAccessToken(tokenInfo.getTokenValue());
        return dto;
    }

}
