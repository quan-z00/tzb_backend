package com.tzb.backend.domain;


import cn.dev33.satoken.secure.SaSecureUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 29002
 */
@Entity
@Getter
@Setter
public class User {
    @Id
    private int id;
    private String username;
    private String password;

    //加密密码
    public void encryptPassword() {
        this.password = SaSecureUtil.sha256(this.password);
    }
}
