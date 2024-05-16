package com.tzb.backend.web.controller;


import com.tzb.backend.web.domain.entity.CulturalManage;
import com.tzb.backend.web.exception.NotExistException;
import com.tzb.backend.web.service.CulturalManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cultural")
public class CulturalManageController {

    @Autowired
    CulturalManageService culturalManageService;

    @GetMapping("/{currentPage}/{pageSize}")
    public List<CulturalManage> selectPages(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize){
        List<CulturalManage> list = culturalManageService.queryAll(currentPage, pageSize);
        if (list==null){
            throw new NotExistException("访问出错!!");
        }else{
            return list;
        }
    }
}
