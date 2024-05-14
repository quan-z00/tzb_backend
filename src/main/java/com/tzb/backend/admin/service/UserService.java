package com.tzb.backend.admin.service;

import com.tzb.backend.admin.domain.dto.UserPageDto;
import com.tzb.backend.admin.domain.request.LoginRequest;
import com.tzb.backend.admin.domain.request.RegisterRequest;
import com.tzb.backend.admin.domain.request.UpdateUserProfileRequest;
import com.tzb.backend.admin.domain.request.UserPageRequest;

import java.util.List;

/**
 * @author 29002
 * @since 2024/5/14
 */
public interface UserService {
    List<UserPageDto> getPage(UserPageRequest userPageRequest);

    void updateUser(UpdateUserProfileRequest updateUserProfileRequest);

    String login(LoginRequest request);

    String register(RegisterRequest request);
}
