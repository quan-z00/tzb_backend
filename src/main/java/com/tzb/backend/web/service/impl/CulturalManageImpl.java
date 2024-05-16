package com.tzb.backend.web.service.impl;


import com.tzb.backend.web.domain.entity.CulturalManage;
import com.tzb.backend.web.mapper.CulturalManageMapper;
import com.tzb.backend.web.service.CulturalManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CulturalManageImpl implements CulturalManageService {

    @Autowired
    CulturalManageMapper culturalManageMapper;

    @Override
    public int totalCount() {
        return culturalManageMapper.totalCount();
    }

    @Override
    public List<CulturalManage> queryAll(int currentPage, int pageSize) {
        List<CulturalManage> users = culturalManageMapper.queryAll(currentPage,pageSize);
        int count = totalCount();
        int startCurrentPage = (currentPage - 1) * pageSize;        //开启的数据
        int endCurrentPage = currentPage * pageSize;        //结束的数据
        int totalPage = count / pageSize;                   //总页数
        if (currentPage > totalPage || currentPage <= 0) {
            return null;
        } else {
            return users.subList(startCurrentPage, endCurrentPage);
        }
    }


}
