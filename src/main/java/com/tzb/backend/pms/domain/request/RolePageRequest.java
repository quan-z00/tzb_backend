package com.tzb.backend.pms.domain.request;

import com.tzb.backend.common.request.MyPageRequest;
import lombok.Data;

/**
 * 分页
 */
@Data
public class RolePageRequest extends MyPageRequest {

    private String name;

    private Boolean enable;

}
