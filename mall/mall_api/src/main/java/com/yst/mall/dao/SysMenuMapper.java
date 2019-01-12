package com.yst.mall.dao;

import com.yst.mall.bean.extend.SysMenuExtend;
import com.yst.mall.model.SysMenu;
import com.yst.mall.model.SysMenuExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysMenuMapper {
    int countByExample(SysMenuExample example);

    int deleteByExample(SysMenuExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    List<SysMenu> selectByExample(SysMenuExample example);

    SysMenu selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByExample(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    
    /**
     * 分页获取菜单
     * */
    List<SysMenuExtend> getAll(@Param("start") int start,
                                   @Param("end") int end,
                                   @Param("menuName") String menuName,
                                   @Param("menuCode") String menuCode,
                                   @Param("parentMenuId") String parentMenuId);

    /**
     * 获取菜单大小
     * */
    int countGetAll( @Param("menuName") String menuName,
                     @Param("menuCode") String menuCode,
                     @Param("parentMenuId") String parentMenuId);

    /**
     * 获取一级菜单列表
     * */
    List<SysMenu> getFirstClassMenus();

    /**
     * 根据菜单名称获取菜单
     * @param menuName 菜单名称
     */
    SysMenu getByMenuName(@Param("menuName") String menuName);

    /**
     * 获取一级菜单最大CODE
     * */
    String getMaxCodeOfFirstClass();

    /**
     * 获取二级菜单最大CODE
     * @param parentMenuId 父菜单id
     * */
    String getMaxCodeOfSecondClass(@Param("parentMenuId") String parentMenuId);

    /**
     * 获取子菜单个数
     * @param menuId 父菜单id
     * */
    int getChildrenCount(@Param("menuId") String menuId);

    /**
     * 根据主键获取菜单信息
     * @param id 菜单主键
     * */
    SysMenuExtend getById(@Param("id") String id);

    /**
     * 根据后台请求路径获取记录
     * @param requestUrl 后台请求路径
     * */
    SysMenu getByRequestUrl(@Param("requestUrl") String requestUrl);

    
}