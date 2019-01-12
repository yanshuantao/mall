package com.yst.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yst.mall.model.SysMenu;
import com.yst.mall.model.SysMenuRole;
import com.yst.mall.model.SysMenuRoleExample;


public interface SysMenuRoleMapper {
    int countByExample(SysMenuRoleExample example);

    int deleteByExample(SysMenuRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysMenuRole record);

    int insertSelective(SysMenuRole record);

    List<SysMenuRole> selectByExample(SysMenuRoleExample example);

    SysMenuRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysMenuRole record, @Param("example") SysMenuRoleExample example);

    int updateByExample(@Param("record") SysMenuRole record, @Param("example") SysMenuRoleExample example);

    int updateByPrimaryKeySelective(SysMenuRole record);

    int updateByPrimaryKey(SysMenuRole record);
    
    /**
     * 根据角色获取可见菜单列表
     * @param roleIds 角色id列表
     * */
    List<SysMenu> getDistinctMenusByRoleIds(List<String> roleIds);

    /**
     * 根据菜单id删除记录
     * @param menuId 菜单id
     */
    int deleteByMenuId(@Param("menuId") String menuId);

    /**
     * 根据菜单id和角色Id获取记录
     * @param menuId 菜单id
     * @param roleId 角色id
     * */
    SysMenuRole selectByMenuIdAndRoleId(@Param("menuId") String menuId,
                                            @Param("roleId") String roleId);

    /**
     * 获取菜单对应的角色id列表
     * @param menuId 菜单id
     * */
    List<String> getRoleIdsByMenuId(@Param("menuId") String menuId);
}