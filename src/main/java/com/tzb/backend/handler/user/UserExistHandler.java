package com.tzb.backend.handler.user;

import com.tzb.backend.constant.ExceptionEnum;
import com.tzb.backend.core.CustomException;
import com.tzb.backend.domain.User;
import com.tzb.backend.handler.Handler;

/**
 * 检查用户是否存在
 * @author 29002
 */
public class UserExistHandler implements Handler {

    private final User user;

    public UserExistHandler(User user) {
        this.user = user;
    }

    @Override
    public void process() {
        if (user != null) {
            throw new CustomException(ExceptionEnum.USER_EXIST);
        }
    }
}
