/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhcategory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.TreeEntity;

/**
 * 商品分类Entity
 * @author wujianbing
 * @version 2019-04-03
 */
public class XhCategory extends TreeEntity<XhCategory> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 商品种类
	private XhCategory parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	private Integer sort;		// 排序
	
	public XhCategory() {
		super();
	}

	public XhCategory(String id){
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonBackReference
	@NotNull(message="父级编号不能为空")
	public XhCategory getParent() {
		return parent;
	}

	public void setParent(XhCategory parent) {
		this.parent = parent;
	}
	
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@NotNull(message="排序不能为空")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}