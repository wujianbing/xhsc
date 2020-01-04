/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhrule.entity;

import com.jeeplus.modules.xhgoods.entity.XhGoods;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 商品规格Entity
 * @author wujianbing
 * @version 2019-04-15
 */
public class XhRule extends DataEntity<XhRule> {
	
	private static final long serialVersionUID = 1L;
	private XhGoods xhGoods;		// 商品信息
	private String rule;		// 商品规格
	private BigDecimal unitPrice; //商品价格
	public XhRule() {
		super();
	}

	public XhRule(String id){
		super(id);
	}

	@ExcelField(title="商品信息", dictType="", align=2, sort=7)
	public XhGoods getXhGoods() {
		return xhGoods;
	}

	public void setXhGoods(XhGoods xhGoods) {
		this.xhGoods = xhGoods;
	}
	
	@Length(min=0, max=255, message="商品规格长度必须介于 0 和 255 之间")
	@ExcelField(title="商品规格", align=2, sort=8)
	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
}