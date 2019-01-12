package com.yst.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yst.mall.model.SysRole;
import com.yst.mall.model.SysRoleExample;

public interface SysRoleMapper {
    int countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    /**
     * 获取所有角色
     * @param start 开始
     * @param end 结束
     * */
    List<SysRole> selectAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("roleName") String roleName,
            @Param("isSuper") String isSuper);


    /**
     * 获取所有角色个数
     * */
    int countGetAll( @Param("roleName") String roleName,
                     @Param("isSuper") String isSuper);

    /**
     * 根据角色名称获取记录
     * @param roleName 角色名称
     * */
    SysRole getByRoleName(@Param("roleName") String roleName);

    /**
     * 根据id获取角色列表
     * @param ids id列表
     * */
    List<String> getRoleNamesByIds(@Param("ids") List<String> ids);
}