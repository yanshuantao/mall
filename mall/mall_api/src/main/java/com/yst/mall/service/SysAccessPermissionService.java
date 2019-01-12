package com.yst.mall.service;


import java.util.List;

import com.yst.mall.model.SysAccessPermission;

/**
 * 访问权限服务
 * Created by gameloft9 on 2017/12/4.
 */
public interface SysAccessPermissionService {

    /**
     * 获取所有访问权限配置
     * */
    List<SysAccessPermission> getAll();
}
