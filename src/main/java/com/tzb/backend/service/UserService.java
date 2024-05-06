package com.tzb.backend.service;

import com.tzb.backend.domain.User;

/**
 * @author 29002
 */
public interface UserService {
    String login(User user);
    String register(User user);
}
