package com.tzb.backend.service;

import com.tzb.backend.domain.User;
import com.tzb.backend.dto.LoginDTO;

/**
 * @author 29002
 */
public interface UserService {
    String login(LoginDTO loginDTO);
    String register(User user);
}
