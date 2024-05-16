package com.tzb.backend.web.service;

import com.tzb.backend.web.domain.entity.CulturalManage;

import java.util.List;

public interface CulturalManageService {


    int totalCount();

    List<CulturalManage> queryAll(int currentPage, int pageSize);
}
