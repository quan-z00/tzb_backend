package com.tzb.backend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.tzb.backend.domain.User;
import com.tzb.backend.dto.user.LoginDTO;
import com.tzb.backend.handler.HandlerBuilder;
import com.tzb.backend.handler.user.UserEncryptPasswordHandler;
import com.tzb.backend.handler.user.UserExistHandler;
import com.tzb.backend.handler.user.UserNotExistHandler;
import com.tzb.backend.handler.user.UserPasswordHandler;
import com.tzb.backend.repository.UserRepository;
import com.tzb.backend.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author 29002
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String login(LoginDTO loginDTO) {
        User dbUser = userRepository.findByUsername(loginDTO.getUsername());
        //责任链模式
        new HandlerBuilder()
                .addHandler(new UserNotExistHandler(dbUser))
                .addHandler(new UserPasswordHandler(loginDTO,dbUser))
                .build();
        StpUtil.login(loginDTO.getPassword());
        return StpUtil.getTokenValue();
    }

    @Override
    public String register(User user) {
        User dbUser = userRepository.findByUsername(user.getUsername());
        //TODO 处理更多信息 密码位数 强度等
        new HandlerBuilder()
                .addHandler(new UserExistHandler(dbUser))
                .addHandler(new UserEncryptPasswordHandler(user))
                .build();
        userRepository.save(user);
        StpUtil.login(user.getUsername());
        return StpUtil.getTokenValue();
    }
}
