package com.tzb.backend.common.auth;

import java.lang.annotation.*;

/**
 * 标识角色权限
 * @author 29002
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Roles {

    /**
     * role编码
     *
     * @return role编码
     */
    String[] value();

}
