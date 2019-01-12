package com.yst.mall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yst.mall.model.SysUser;

/**
 * Created by gameloft9 on 2017/11/28.
 */
public interface SysUserMapper {

    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 查找用户
     *
     * @param loginName 登录名
     * @param password  密码（为了不增加复杂度，这里不进行加密，使用明文）
     */
    Integer countUserByNameAndPwd(@Param("loginName") String loginName,
                                  @Param("password") String password);

    /**
     * 根据loginName获取用户
     *
     * @param loginName 登录名
     */
    SysUser getByLoginName(@Param("loginName") String loginName);

    /**
     * 分页获取用户列表
     */
    List<SysUser> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("loginName") String loginName,
            @Param("realName") String realName,
            @Param("isForbidden") String isForbidden);

    /**
     * 获取用户列表大小
     */
    int countGetAll(@Param("loginName") String loginName,
                    @Param("realName") String realName,
                    @Param("isForbidden") String isForbidden);
}
