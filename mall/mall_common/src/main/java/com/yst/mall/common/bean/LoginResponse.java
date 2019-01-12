package com.yst.mall.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录成功返回bean
 * Created by gameloft9 on 2017/12/11.
 */
@Data
public class LoginResponse implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2423280793780105109L;
	/**
     * 用户id
     * */
    private String userId;
    /**
     * 登录名
     * */
    private String loginName;

    /**
     * web上下文
     * */
    private String webContext;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getWebContext() {
		return webContext;
	}

	public void setWebContext(String webContext) {
		this.webContext = webContext;
	}
    
}
