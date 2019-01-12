package com.yst.mall.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * 返回给layui前端的菜单树
 * Created by gameloft9 on 2017/12/7.
 */
@Data
public class MenuTreeResponse implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 279860960383302880L;
	private String id;
    /**标题*/
    private String title;
    /**链接*/
    private String href;
    /**图标名称*/
    private String icon;
    /**目标窗口*/
    private String target;
    /**是否展开*/
    private Boolean spread;
    /**子菜单*/
    private List<MenuTreeResponse> children;

    /**
     * 构造函数
     * */
    public MenuTreeResponse(){
        this.children = new LinkedList<MenuTreeResponse>();
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Boolean getSpread() {
		return spread;
	}

	public void setSpread(Boolean spread) {
		this.spread = spread;
	}

	public List<MenuTreeResponse> getChildren() {
		return children;
	}

	public void setChildren(List<MenuTreeResponse> children) {
		this.children = children;
	}
}
