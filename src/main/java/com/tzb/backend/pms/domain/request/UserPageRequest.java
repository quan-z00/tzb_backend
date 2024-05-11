package com.tzb.backend.pms.domain.request;

import com.tzb.backend.common.request.MyPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户分页查询
 *
 * @author dhb
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPageRequest extends MyPageRequest {

    private String username;

    private Integer gender;

    private Integer role;

    private Boolean enable;


}
