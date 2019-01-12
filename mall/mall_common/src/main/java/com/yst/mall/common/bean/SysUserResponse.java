package com.yst.mall.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 返回给前端的用户信息
 * Created by gameloft9 on 2017/12/21.
 */
@Data
public class SysUserResponse implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4893167330083131767L;
	private String id;
    /**登录名*/
    private String loginName;
    /**真实姓名*/
    private String realName;
    /**所属机构id*/
    private String orgId;
    /**所属机构名称*/
    private String orgName;
    /**手机号码*/
    private String mobile;
    /**所属角色列表*/
    private List<String> roleIdList;

    /**
     * 构造函数
     * */
    public SysUserResponse(){
        this.roleIdList = new ArrayList<String>();
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}
    
}
