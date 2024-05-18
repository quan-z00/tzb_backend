package com.tzb.backend.web.specification;

import com.tzb.backend.web.domain.dto.DIYshareCommentDTO;
import com.tzb.backend.web.domain.entity.DIYshareComment;
import org.springframework.data.jpa.domain.Specification;

public class DIYshareCommentSpecification {
    public static Specification<DIYshareCommentDTO> withDynamicQuery(String searchField, Object searchValue) {
        return (root, query, builder) -> {
            if (searchValue == null) {
                return builder.conjunction(); // 如果搜索值为空，则返回所有结果
            }
//            return builder.equal(root.get(searchField), searchValue); // 根据搜索字段和搜索值构建等值查询条件
//             对于字符串类型的字段，可以使用like模糊查询
             return builder.like(root.get(searchField), "%" + searchValue + "%");
        };
    }
}
