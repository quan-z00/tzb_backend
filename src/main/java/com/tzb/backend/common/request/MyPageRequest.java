package com.tzb.backend.common.request;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 分页请求
 */
@Data
public class MyPageRequest {

    /**
     * 页数
     */
    private Integer pageNo = 1;

    /**
     * 页大小
     */
    private Integer pageSize = 10;

    /**
     * 转换成 JPA 的 Pageable 对象
     *
     * @return Pageable
     */
    public Pageable toPageable() {
        return PageRequest.of(pageNo - 1, pageSize);
    }

    public Pageable toPageable(Sort sort) {
        return PageRequest.of(pageNo - 1, pageSize, sort);
    }
}
