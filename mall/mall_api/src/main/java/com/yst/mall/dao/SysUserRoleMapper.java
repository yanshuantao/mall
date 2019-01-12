package com.yst.mall.dao;

import com.yst.mall.model.SysRole;
import com.yst.mall.model.SysUserRole;
import com.yst.mall.model.SysUserRoleExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysUserRoleMapper {
    int countByExample(SysUserRoleExample example);

    int deleteByExample(SysUserRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    List<SysUserRole> selectByExample(SysUserRoleExample example);

    SysUserRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example);

    int updateByExample(@Param("record") SysUserRole record, @Param("example") SysUserRoleExample example);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
    
    /**
     * 根据用户id获取角色列表
     * @param userId 用户id
     * */
    List<String> getRoleNamesByUserId(@Param("userId") String userId);

    /**
     * 根据用户id获取角色列表
     * @param userId 用户id
     * */
    List<SysRole> getRolesByUserId(@Param("userId") String userId);

    /**
     * 根据角色id获取用户数
     * @param roleId 角色id
     * */
    int countByRoleId(@Param("roleId") String roleId);

    /**
     * 批量插入
     * @param list 记录列表
     * */
    int batchInsert(@Param("list") List<SysUserRole> list);

    /**
     * 根据用户id删除
     * @param userId 用户id
     * */
    int deleteByUserId(@Param("userId") String userId);
}