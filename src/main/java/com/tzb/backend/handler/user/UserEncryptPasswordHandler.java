package com.tzb.backend.handler.user;

import com.tzb.backend.domain.User;
import com.tzb.backend.handler.Handler;

/**
 * 加密用户密码
 * @author 29002
 */
public class UserEncryptPasswordHandler implements Handler {
    private final User user;

    public UserEncryptPasswordHandler(User user) {
        this.user = user;
    }

    @Override
    public void process() {
        user.encryptPassword();
    }
}
