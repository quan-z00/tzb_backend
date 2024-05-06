package com.tzb.backend.handler.user;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.tzb.backend.constant.ExceptionEnum;
import com.tzb.backend.core.CustomException;
import com.tzb.backend.domain.User;
import com.tzb.backend.handler.Handler;

/**
 * 检查用户密码
 * @author 29002
 */
public class UserPasswordHandler implements Handler {

    private final User user;
    private final User dbUser;

    public UserPasswordHandler(User user, User dbUser) {
        this.user = user;
        this.dbUser = dbUser;
    }

    @Override
    public void process() {
        if (!SaSecureUtil.sha256(user.getPassword()).equals(dbUser.getPassword())) {
            throw new CustomException(ExceptionEnum.USER_PASSWORD_ERROR);
        }
    }
}
