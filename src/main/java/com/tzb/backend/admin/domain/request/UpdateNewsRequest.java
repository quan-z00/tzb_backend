package com.tzb.backend.admin.domain.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * @author 29002
 * @since 2024/5/17
 */
@Data
public class UpdateNewsRequest {
    @NotNull(message = "id不能为空")
    private Integer id;
    private String title;
    private String author;
    private String content;
    private String imageUrl;
    private Long createdAt;

    @Min(value = 0, message = "状态值不能小于0")
    @Max(value = 2, message = "状态值不能大于2")
    private Integer status;
}
