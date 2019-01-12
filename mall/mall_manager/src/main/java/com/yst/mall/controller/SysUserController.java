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
import com.yst.mall.common.bean.UserAddRequest;
import com.yst.mall.common.bean.UserUpdateRequest;
import com.yst.mall.common.constant.OperType;
import com.yst.mall.common.data.ResultData;
import com.yst.mall.common.response.IResult;
import com.yst.mall.common.response.PageResultBean;
import com.yst.mall.common.response.ResultBean;
import com.yst.mall.model.SysUser;
import com.yst.mall.service.SysUserService;

/**
 * 系统用户
 * Created by gameloft9 on 2017/12/20.
 */
@Slf4j
@Service
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    SysUserService sysUserServiceImpl;

    /**
     * 获取所有用户列表
     * @param page 页序
     * @param limit 分页大小
     * */
    @RequestMapping(value = "/userList.do",method = RequestMethod.POST)
    @ResponseBody
    public ResultData getUserList(String page, String limit, String loginName, String realName, String status){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultData<Collection<SysUser>>(sysUserServiceImpl.getAll(page,limit,loginName,realName,status),sysUserServiceImpl.countGetAll(loginName,realName,status));
    }


    /**
     * 添加用户
     * */
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "添加用户")
    public IResult addUser(@RequestBody UserAddRequest request){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<String>(sysUserServiceImpl.addUser(request));
    }

    /**
     * 删除用户
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除用户")
    public IResult deleteUser(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysUserServiceImpl.deleteById(id));
    }

    /**
     * 获取用户
     * */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getUser(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<SysUserResponse>(sysUserServiceImpl.getById(id));
    }

    /**
     * 更新用户
     * */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "更新用户")
    public IResult updateUser(@RequestBody @Valid UserUpdateRequest user, BindingResult result){//传递了数组，前台放在payload里面了，后台通过@RequestBody获取
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysUserServiceImpl.updateUser(user));
    }


    /**
     * 初始化密码
     * */
    @RequestMapping(value = "/initPwd.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "初始化密码")
    public IResult initPwd(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<String>(sysUserServiceImpl.initPwd(id));
    }

    /**
     * 修改密码
     * */
    @RequestMapping(value = "/changePwd.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "更新密码")
    public IResult changePwd(String loginName,String oldPwd,String newPwd){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysUserServiceImpl.changePwd(loginName,oldPwd,newPwd));
    }

}
