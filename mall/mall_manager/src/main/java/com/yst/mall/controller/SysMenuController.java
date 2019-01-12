package com.yst.mall.controller;

import java.util.Collection;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.mall.bean.extend.SysMenuExtend;
import com.yst.mall.common.annotation.BizOperLog;
import com.yst.mall.common.bean.MenuTreeResponse;
import com.yst.mall.common.bean.MenuUpdateRequest;
import com.yst.mall.common.constant.OperType;
import com.yst.mall.common.data.ResultData;
import com.yst.mall.model.SysMenu;
import com.yst.mall.model.SysUser;
import com.yst.mall.service.SysMenuService;

@Controller
@Slf4j
@RequestMapping("/menu")
public class SysMenuController {
	
	@Autowired
	private SysMenuService sysMenuService;
	
	 /**
     * 获取所有菜单列表
     * @param page 页序
     * @param limit 分页大小
     * */
    @RequestMapping(value = "/menuList.do",method = RequestMethod.POST)
    @ResponseBody
    //@BizOperLog(operType = OperType.Query,memo = "获取所有菜单列表")
    public ResultData getMenuList(String page, String limit, String menuName, String menuCode, String parentMenuId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultData<Collection<SysMenuExtend>>(sysMenuService.getAll(page,limit,menuName,menuCode,parentMenuId),
        			sysMenuService.countGetAll(menuName,menuCode,parentMenuId));
    }

    /**
     * 获取所有一级菜单列表
     * */
    @RequestMapping(value = "/firstClassMenus.do",method = RequestMethod.POST)
    @ResponseBody
    //@BizOperLog(operType = OperType.Query,memo = "获取所有一级菜单列表")
    public ResultData getFirstMenuList(){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultData<Collection<SysMenu>>(sysMenuService.getFirstClassMenus());
    }

    /**
     * 获取角色可见菜单列表
     * */
    @RequestMapping(value = "/menuList.do",method = RequestMethod.GET)
    @ResponseBody
    //@BizOperLog(operType = OperType.Query,memo = "获取角色菜单列表")
    public ResultData getMenuList(){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultData<Collection<MenuTreeResponse>>(sysMenuService.getMenuByRoles((SysUser) SecurityUtils.getSubject().getPrincipal()));
    }

    /**
     * 添加菜单
     * */
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "添加菜单")
    public ResultData addMenu(String menuName,String menuUrl,String menuType,String parentMenuId,String requestUrl,String sort){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultData<String>(sysMenuService.addMenu(menuName,menuUrl,menuType,parentMenuId,requestUrl,sort));
    }

    /**
     * 删除菜单
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除菜单")
    public ResultData deleteMenu(String menuId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultData<Boolean>(sysMenuService.deleteMenu(menuId));
    }

    /**
     * 获取菜单
     * */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public ResultData getMenu(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultData<SysMenuExtend>(sysMenuService.getById(id));
    }

    /**
     * 更新菜单
     * @param id 菜单id
     * @param menuName 菜单名称
     * @param menuUrl 菜单访问链接
     * @param requestUrl 后台访问路径
     * @param idList 角色id列表
     * @param sort 排序号
     * */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "更新菜单")
    public ResultData updateMenu(@RequestBody MenuUpdateRequest menu){//传递了数组，前台放在payload里面了，后台通过@RequestBody获取
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultData<Boolean>(sysMenuService.updateMenu(menu.getId(),menu.getMenuName(),menu.getMenuUrl(),menu.getRequestUrl(),menu.getSort(),menu.getRoleIdList()));
    }

}
