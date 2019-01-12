package com.yst.mall.controller;

import java.util.Collection;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.mall.common.annotation.BizOperLog;
import com.yst.mall.common.bean.SysUserResponse;
import com.yst.mall.common.bean.UserUpdateRequest;
import com.yst.mall.common.constant.OperType;
import com.yst.mall.common.data.ResultData;
import com.yst.mall.common.response.IResult;
import com.yst.mall.common.response.PageResultBean;
import com.yst.mall.common.response.ResultBean;
import com.yst.mall.model.SysRole;
import com.yst.mall.service.SysRoleService;
import com.yst.mall.service.SysUserService;

/**
 * 个人中心
 * Created by gameloft9 on 2018/1/11.
 */
@Slf4j
@Service
@RequestMapping("/personCenter")
public class PersonCenterController {

    @Autowired
    SysUserService sysUserServiceImpl;

    @Autowired
    SysRoleService sysRoleServiceImpl;

    /**
     * 获取个人信息
     * */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getUser(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<SysUserResponse>(sysUserServiceImpl.getById(id));
    }

    /**
     * 更新个人信息
     * */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "更新个人信息")
    public IResult updateUser(@RequestBody @Valid UserUpdateRequest user, BindingResult result){//传递了数组，前台放在payload里面了，后台通过@RequestBody获取
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysUserServiceImpl.updateUser(user));
    }

    /**
     * 修改密码
     * */
    @RequestMapping(value = "/changePwd.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "更新个人密码")
    public IResult changePwd(String loginName,String oldPwd,String newPwd){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysUserServiceImpl.changePwd(loginName,oldPwd,newPwd));
    }

    /**
     * 获取所有角色列表
     * @param page 页序
     * @param limit 分页大小
     * */
    @RequestMapping(value = "/roleList.do",method = RequestMethod.POST)
    @ResponseBody
    //@BizOperLog(operType = OperType.Query,memo = "获取所有角色列表")
    public ResultData<Collection<SysRole>> getRoleList(String page,String limit,String roleName,String isSuper){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultData<Collection<SysRole>>(sysRoleServiceImpl.getAll(page,limit,roleName,isSuper),sysRoleServiceImpl.countGetAll(roleName,isSuper));
    }
}
