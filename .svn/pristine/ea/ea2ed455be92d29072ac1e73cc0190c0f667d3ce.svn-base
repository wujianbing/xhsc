/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhmonthbuy.entity;

import com.jeeplus.modules.xhgoods.entity.XhGoods;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 包月商品Entity
 * @author wujianbing
 * @version 2019-04-17
 */
public class XhMonthbuy extends DataEntity<XhMonthbuy> {
	
	private static final long serialVersionUID = 1L;
	private XhGoods xhGoods;		// 包月商品
	private String mTitle;		// 包月描述
	private String lTitle;     //描述二
	private BigDecimal mPrice;		// 包月单价
	private String mStatus;	// 包月状态
	public XhMonthbuy() {
		super();
	}

	public XhMonthbuy(String id){
		super(id);
	}

	@ExcelField(title="包月商品", align=2, sort=6)
	public XhGoods getXhGoods() {
		return xhGoods;
	}

	public void setXhGoods(XhGoods xhGoods) {
		this.xhGoods = xhGoods;
	}
	
	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public String getlTitle() {
		return lTitle;
	}

	public void setlTitle(String lTitle) {
		this.lTitle = lTitle;
	}

	public BigDecimal getmPrice() {
		return mPrice;
	}

	public void setmPrice(BigDecimal mPrice) {
		this.mPrice = mPrice;
	}

	public String getmStatus() {
		return mStatus;
	}
	@ExcelField(title="包月状态",dictType="mStatus")
	public void setmStatus(String mStatus) {
		this.mStatus = mStatus;
	}
}