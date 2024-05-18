package com.tzb.backend.admin.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author 29002
 * @since 2024/5/17
 */
@Data
public class DeleteNewsRequest {
    @NotNull(message = "id不能为空")
    private Integer id;
}
