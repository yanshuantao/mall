package com.yst.mall.model;

import java.io.Serializable;
import java.util.Date;

public class GoodsCategory implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7596956558835901654L;

	private Integer id;

    private String name;

    private Integer parentId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}