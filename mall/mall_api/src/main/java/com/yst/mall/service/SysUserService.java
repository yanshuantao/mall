package com.yst.mall.service;

import java.util.List;

import com.yst.mall.common.bean.SysUserResponse;
import com.yst.mall.common.bean.UserAddRequest;
import com.yst.mall.common.bean.UserUpdateRequest;
import com.yst.mall.common.data.ResultData;
import com.yst.mall.model.SysUser;
import com.yst.mall.model.SysUserExample;

public interface SysUserService {
	
	ResultData<SysUser> loginDo(SysUserExample example);
	/**
     * 获取用户角色列表
     * @param userId 用户id
     * */
    public List<String> getRoleNames(String userId);
    
//    public SysUser getByLoginName(SysUserExample example);
    
    /**
     * 验证用户
     * @param loginName 登录名
     * @param password 密码（为了不增加复杂度，这里不进行加密，使用明文）
     * */
    Boolean validateUser(String loginName,String password);

    /**
     * 根据登录名获取用户
     * @param loginName 登录名
     * */
    SysUser getByLoginName(String loginName);


    /**
     * 分页获取用户列表
     *
     * @param page         页序
     * @param limit        分页大小
     * @param loginName     登录名
     * @param realName     姓名
     * @param status       状态 0-禁用 1-启用
     */
    List<SysUser> getAll(String page, String limit, String loginName, String realName, String status);

    /**
     * 获取用户列表大小
     *
     * @param loginName     登录名
     * @param realName     姓名
     * @param status       状态 0-禁用 1-启用
     */
    int countGetAll(String loginName, String realName, String status);

    /**
     * 根据id删除用户
     * @param id 用户id
     * */
    Boolean deleteById(String id);

    /**
     * 根据id获取记录
     * @param id 主键
     * */
    SysUserResponse getById(String id);

    /**
     * 更新
     * @param user 记录
     * */
    Boolean updateUser(UserUpdateRequest user);

    /**
     * 添加用户
     * @param user 记录
     * @return String 主键id
     * */
    String addUser(UserAddRequest user);

    /**
     * 初始化密码
     * @param userId 用户id
     * */
    String initPwd(String userId);

    /**
     * 更换密码
     * @param  loginName 用户登录名
     * @param  newPwd 新密码
     * */
    Boolean changePwd(String loginName,String oldPwd,String newPwd);
}
