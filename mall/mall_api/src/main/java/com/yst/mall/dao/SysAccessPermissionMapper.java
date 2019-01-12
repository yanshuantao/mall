package com.yst.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yst.mall.model.SysAccessPermission;
import com.yst.mall.model.SysAccessPermissionExample;

public interface SysAccessPermissionMapper {
    int countByExample(SysAccessPermissionExample example);

    int deleteByExample(SysAccessPermissionExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysAccessPermission record);

    int insertSelective(SysAccessPermission record);

    List<SysAccessPermission> selectByExample(SysAccessPermissionExample example);

    SysAccessPermission selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysAccessPermission record, @Param("example") SysAccessPermissionExample example);

    int updateByExample(@Param("record") SysAccessPermission record, @Param("example") SysAccessPermissionExample example);

    int updateByPrimaryKeySelective(SysAccessPermission record);

    int updateByPrimaryKey(SysAccessPermission record);
    
    /**
     * 获取所有访问权限配置
     * */
    List<SysAccessPermission> selectAll();
}