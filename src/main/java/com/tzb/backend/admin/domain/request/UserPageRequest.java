package com.tzb.backend.admin.domain.request;

import com.tzb.backend.common.request.MyPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 29002
 * @since 2024/5/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPageRequest extends MyPageRequest {
    private String username;
    private String email;
    private Boolean enable;
    private Integer type;
}
