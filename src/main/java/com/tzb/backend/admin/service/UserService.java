package com.tzb.backend.admin.service;

import com.tzb.backend.admin.domain.request.*;
import com.tzb.backend.common.core.PageResponse;

/**
 * @author 29002
 * @since 2024/5/14
 */
public interface UserService {
    PageResponse getPage(UserPageRequest userPageRequest);

    void updateUser(UpdateUserProfileRequest updateUserProfileRequest);

    String login(LoginRequest request);

    String register(RegisterRequest request);

    void updateUserStatus(Integer userId, UpdateUserStatusRequest request);
}
