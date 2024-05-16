package com.tzb.backend.web.mapper;


import com.tzb.backend.web.domain.entity.CulturalManage;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CulturalManageMapper {

    Integer totalCount();

    //    List<CulturalManage> queryAll();
    List<CulturalManage> queryAll(Integer currentPage, Integer pageSize);

}
