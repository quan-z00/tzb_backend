package com.tzb.backend.admin.domain.request;

import com.tzb.backend.common.request.MyPageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author 29002
 * @since 2024/5/16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostPageRequest extends MyPageRequest {
    private String title;
    private String nickname;
    private Integer status;
    private List<Integer> userIds;
}
