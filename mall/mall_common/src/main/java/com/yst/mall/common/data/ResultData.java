package com.yst.mall.common.data;

import java.io.Serializable;

public class ResultData<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7517850651626137333L;
	
    /**成功Code*/
    public static final String SUCCESS = "0000";
    /**系统失败Code*/
    public static final String SYSTEM_FAIL = "9999";
    /**检查失败Code*/
    public static final String CHECK_FAIL = "9100";
    /**业务失败Code*/
    public static final String BIZ_FAIL = "9200";

	private String code;
	
	private String msg;
	
	private T data;
	
	private Integer count;
	
	
	public ResultData(){
		super();
	}
	
	public ResultData(T data) {
		super();
		this.code = SUCCESS;
		this.data = data;
	}
	
	public ResultData(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public ResultData(T data, Integer count) {
		super();
		this.code = SUCCESS;
		this.data = data;
		this.count = count;
	}

	public ResultData(String code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
