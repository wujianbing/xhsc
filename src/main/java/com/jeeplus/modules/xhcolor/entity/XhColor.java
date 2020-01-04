/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhcolor.entity;

import com.jeeplus.modules.xhgoods.entity.XhGoods;
import org.hibernate.validator.constraints.Length;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 商品颜色Entity
 * @author wujianbing
 * @version 2019-04-23
 */
public class XhColor extends DataEntity<XhColor> {
	
	private static final long serialVersionUID = 1L;
	private XhGoods xhGoods;		// 商品信息
	private String color;		// 鲜花颜色
	
	public XhColor() {
		super();
	}

	public XhColor(String id){
		super(id);
	}

	@ExcelField(title="商品信息", dictType="", align=2, sort=7)
	public XhGoods getXhGoods() {
		return xhGoods;
	}

	public void setXhGoods(XhGoods xhGoods) {
		this.xhGoods = xhGoods;
	}
	
	@Length(min=0, max=64, message="鲜花颜色长度必须介于 0 和 64 之间")
	@ExcelField(title="鲜花颜色", align=2, sort=8)
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
}