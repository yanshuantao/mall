package com.yst.mall.controller;

import java.util.Collection;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.mall.common.annotation.BizOperLog;
import com.yst.mall.common.constant.OperType;
import com.yst.mall.common.response.IResult;
import com.yst.mall.common.response.PageResultBean;
import com.yst.mall.common.response.ResultBean;
import com.yst.mall.model.SysRole;
import com.yst.mall.service.SysRoleService;

/**
 * 系统角色controller
 * Created by gameloft9 on 2017/12/4.
 */
@Controller
@Slf4j
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    SysRoleService sysRoleServiceImpl;

    /**
     * 获取所有角色列表
     * @param page 页序
     * @param limit 分页大小
     * */
    @RequestMapping(value = "/roleList.do",method = RequestMethod.POST)
    @ResponseBody
    //@BizOperLog(operType = OperType.Query,memo = "获取所有角色列表")
    public IResult getRoleList(String page,String limit,String roleName,String isSuper){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<SysRole>>(sysRoleServiceImpl.getAll(page,limit,roleName,isSuper),sysRoleServiceImpl.countGetAll(roleName,isSuper));
    }

    /**
     * 删除角色
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除角色")
    public IResult deleteRole(String roleId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysRoleServiceImpl.deleteRoleById(roleId));
    }

    /**
     * 添加角色
     * */
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "添加角色")
    public IResult addRole(String roleName,String isSuper){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<String>(sysRoleServiceImpl.addRole(roleName,isSuper));
    }

    /**
     * 更新角色
     * */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "更新角色")
    public IResult editRole(String id,String roleName,String isSuper){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysRoleServiceImpl.updateRole(id,roleName,isSuper));
    }

    /**
     * 获取角色
     * */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getRole(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<SysRole>(sysRoleServiceImpl.getRole(id));
    }
}
