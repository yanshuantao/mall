package com.yst.mall.service;

import java.util.List;

import com.yst.mall.bean.extend.SysMenuExtend;
import com.yst.mall.common.bean.MenuTreeResponse;
import com.yst.mall.model.SysMenu;
import com.yst.mall.model.SysUser;


public interface SysMenuService {
	/**
     * 根据角色获取可见菜单列表
     * */
    List<MenuTreeResponse> getMenuByRoles(SysUser user);

    /**
     * 分页获取菜单列表
     * @param page 页序
     * @param limit 分页大小
     * @param menuName 菜单名称
     * @param menuCode 菜单编码
     * @param parentMenuId 父菜单主键
     * */
    List<SysMenuExtend> getAll(String page, String limit, String menuName, String menuCode, String parentMenuId);

    /**
     * 获取菜单列表大小
     * @param menuName 菜单名称
     * @param menuCode 菜单编码
     * @param parentMenuId 父菜单主键
     * */
    int countGetAll(String menuName, String menuCode,String parentMenuId);

    /**
     * 获取一级菜单列表
     * */
    List<SysMenu> getFirstClassMenus();

    /**
     * 添加菜单
     * @param menuName 菜单名称
     * @param menuUrl 访问链接
     * @param menuType 菜单类型
     * @param parentMenuId 父菜单id
     * @return String 菜单id
     * */
    String addMenu(String menuName,String menuUrl,String menuType,String parentMenuId,String requestUrl,String sort);

    /**
     * 删除菜单
     * @param menuId 菜单id
     * */
    Boolean deleteMenu(String menuId);

    /**
     * 根据主键获取菜单信息
     * @param id 菜单主键
     * */
    SysMenuExtend getById(String id);

    /**
     * 更新菜单
     * @param id 菜单id
     * @param menuName 菜单名称
     * @param menuUrl 链接
     * @param requestUrl 后台访问路径
     * @param sort 排序号
     * @param idList 角色列表
     * */
    Boolean updateMenu(String id,String menuName,String menuUrl,String requestUrl,String sort,List<String> idList);
}