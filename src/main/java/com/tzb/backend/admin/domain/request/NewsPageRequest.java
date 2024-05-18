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
public class NewsPageRequest extends MyPageRequest {
    private String title;
    private String author;
    private Integer status;
}
