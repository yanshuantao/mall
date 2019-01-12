package com.yst.mall.bean.extend;


import java.io.Serializable;
import java.util.List;

import com.yst.mall.model.SysMenu;

/**
 * 返回给客户端的菜单信息
 * Created by gameloft9 on 2017/12/12.
 */
public class SysMenuExtend extends SysMenu implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5225238203878746191L;

	/**
     * 父菜单编码
     * */
    private String parentMenuCode;

    /**
     * 父菜单名称
     * */
    private String parentMenuName;

    /**
     * 菜单所属角色id列表
     * */
    private List<String> roleIdList;

	public String getParentMenuCode() {
		return parentMenuCode;
	}

	public void setParentMenuCode(String parentMenuCode) {
		this.parentMenuCode = parentMenuCode;
	}

	public String getParentMenuName() {
		return parentMenuName;
	}

	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}

}
