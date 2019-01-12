package com.yst.mall.service.impl;

import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yst.mall.common.data.PageRange;
import com.yst.mall.dao.SysRoleMapper;
import com.yst.mall.dao.SysUserRoleMapper;
import com.yst.mall.model.SysRole;
import com.yst.mall.service.SysRoleService;
import com.yst.mall.util.layui.CheckUtil;
import com.yst.mall.util.layui.UUIDUtil;
import com.yst.mall.util.layui.UserInfoUtil;

/**
 * Created by gameloft9 on 2017/12/4.
 */
@Slf4j
@Service
public class SysRoleServiceImpl implements SysRoleService{

    @Autowired
    SysRoleMapper sysRoleDao;
    @Autowired
    SysUserRoleMapper sysUserRoleTestMapper;

    /**
     * 获取所有角色
     * */
    public List<SysRole> getAll(String page, String limit, String roleName, String isSuper){
        PageRange pageRange = new PageRange(page,limit);
        return sysRoleDao.selectAll(pageRange.getStart(),pageRange.getEnd(),roleName,isSuper);
    }

    /**
     * 获取所有角色个数
     * */
    public int countGetAll(String roleName,String isSuper){
        return sysRoleDao.countGetAll(roleName,isSuper);
    }

    /**
     * 删除角色
     * @param roleId  角色id
     * */
    public Boolean deleteRoleById(String roleId){
        //判断该角色下面是否还有用户,有用户则不能删除该角色
        int userCount = sysUserRoleTestMapper.countByRoleId(roleId);
        CheckUtil.check(userCount==0,"该角色下有用户，无法删除");

        sysRoleDao.deleteByPrimaryKey(roleId);
        return true;
    }

    /**
     * 添加角色
     * @param roleName  角色名称
     * @param isSuper   是否超级管理员
     * */
    public String addRole(String roleName,String isSuper){
        CheckUtil.notBlank(roleName,"角色名称为空");
        CheckUtil.notBlank(isSuper,"是否超级管理员不能为空");

        //角色名称不能重复
        SysRole roleTest = sysRoleDao.getByRoleName(roleName);
        CheckUtil.check(roleTest == null,"该角色名称已经存在");

        SysRole role = new SysRole();
        role.setId(UUIDUtil.getUUID());
        role.setRoleName(roleName);
        role.setIsSuper(Integer.valueOf(isSuper));
        role.setCreateUser(UserInfoUtil.getUser().toString());
        sysRoleDao.insertSelective(role);

        return role.getId();
    }


    /**
     * 获取角色
     * @param roleId 角色id
     */
    public SysRole getRole(String roleId){
        CheckUtil.notBlank(roleId,"角色id为空");
        return sysRoleDao.selectByPrimaryKey(roleId);
    }

    /**
     * 更新角色
     * @param roleId 角色id
     * @param roleName 角色名称;
     * @param isSuper 是否超级管理员 1-是，0-否
     * */
    public Boolean updateRole(String roleId,String roleName,String isSuper){
        CheckUtil.notBlank(roleId,"角色id为空");

        //角色名称不能重复
        SysRole roleTest = sysRoleDao.getByRoleName(roleName);
        CheckUtil.check(roleTest == null || roleTest.getRoleName().equals(roleName),"该角色名称已经存在");

        SysRole role = new SysRole();
        role.setId(roleId);
        role.setRoleName(roleName);
        role.setIsSuper(Integer.valueOf(isSuper));
        role.setUpdateUser(UserInfoUtil.getUser().toString());
        role.setUpdateTime(new Date());
        sysRoleDao.updateByPrimaryKeySelective(role);

        return true;
    }
}
