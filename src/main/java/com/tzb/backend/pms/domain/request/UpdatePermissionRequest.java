package com.tzb.backend.pms.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 更新权限
 *
 * @author : 29002
 */
@Data
public class UpdatePermissionRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotBlank
    private String type;


    private Long parentId;

    private String path;

    private String redirect;

    private String icon;

    private String component;

    private String layout;

    private Boolean keepAlive;

    private String method;

    private String description;

    private Boolean show;

    private Boolean enable;

    private Integer order;
}
