package com.yst.mall.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 批量删除日期请求bean
 * Created by gameloft9 on 2017/12/18.
 */
@Data
public class LogBatchDelRequest implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5378497143336518252L;
	/**是否全部删除*/
    private Boolean allDel;
    /**待删除id列表-全部删除时无效*/
    private List<String> delIds;
    /**非删除id列表-仅全部删除时有效*/
    private List<String> notDelIds;
    /**登录名-仅全部删除时有效*/
    private String loginName;
    /**操作类型-仅全部删除时有效*/
    private String operType;
    /**开始时间-仅全部删除时有效*/
    private String startTime;
    /**结束时间-仅全部删除时有效*/
    private String endTime;
	public Boolean getAllDel() {
		return allDel;
	}
	public void setAllDel(Boolean allDel) {
		this.allDel = allDel;
	}
	public List<String> getDelIds() {
		return delIds;
	}
	public void setDelIds(List<String> delIds) {
		this.delIds = delIds;
	}
	public List<String> getNotDelIds() {
		return notDelIds;
	}
	public void setNotDelIds(List<String> notDelIds) {
		this.notDelIds = notDelIds;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getOperType() {
		return operType;
	}
	public void setOperType(String operType) {
		this.operType = operType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
