package com.yst.mall.service.impl;

import com.yst.mall.dao.SysAccessPermissionMapper;
import com.yst.mall.model.SysAccessPermission;
import com.yst.mall.service.SysAccessPermissionService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gameloft9 on 2017/12/4.
 */
@Slf4j
@Service
public class SysAccessPermissionServiceImpl implements SysAccessPermissionService {

    @Autowired
    SysAccessPermissionMapper sysAccessPermissionTestDao;

    public List<SysAccessPermission> getAll(){
        return sysAccessPermissionTestDao.selectAll();
    }


}
