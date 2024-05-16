package com.tzb.backend.common.core;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 29002
 * @since 2024/5/15
 */
@Data
@AllArgsConstructor
public class PageResponse {
    private Object pageData;
    private long total;
}
