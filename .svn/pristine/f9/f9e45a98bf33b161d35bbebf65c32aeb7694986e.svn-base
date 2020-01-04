/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhtime.entity;

import com.jeeplus.modules.xhgoods.entity.XhGoods;
import org.hibernate.validator.constraints.Length;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 收花时间Entity
 * @author wujianbing
 * @version 2019-04-23
 */
public class XhTime extends DataEntity<XhTime> {
	
	private static final long serialVersionUID = 1L;
	private XhGoods xhGoods;		// 商品信息
	private String xhTime;		// 收花时间
	
	public XhTime() {
		super();
	}

	public XhTime(String id){
		super(id);
	}

	@ExcelField(title="商品信息", dictType="", align=2, sort=7)
	public XhGoods getXhGoods() {
		return xhGoods;
	}

	public void setXhGoods(XhGoods xhGoods) {
		this.xhGoods = xhGoods;
	}
	
	@Length(min=0, max=64, message="收花时间长度必须介于 0 和 64 之间")
	@ExcelField(title="收花时间", align=2, sort=8)
	public String getXhTime() {
		return xhTime;
	}

	public void setXhTime(String xhTime) {
		this.xhTime = xhTime;
	}
	
}