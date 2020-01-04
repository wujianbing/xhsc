/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.monthtime.entity;

import com.jeeplus.modules.xhmonthbuy.entity.XhMonthbuy;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 包月时间设定Entity
 * @author wujianbing
 * @version 2019-04-17
 */
public class MonthTime extends DataEntity<MonthTime> {
	
	private static final long serialVersionUID = 1L;
	private XhMonthbuy xhMonthbuy;		// 包月商品
	private String mTime;		// 包月时间设定
	private BigDecimal mPrice; // 包月价格设定
	public MonthTime() {
		super();
	}

	public MonthTime(String id){
		super(id);
	}

	public XhMonthbuy getXhMonthbuy() {
		return xhMonthbuy;
	}

	public void setXhMonthbuy(XhMonthbuy xhMonthbuy) {
		this.xhMonthbuy = xhMonthbuy;
	}

	public String getmTime() {
		return mTime;
	}

	public void setmTime(String mTime) {
		this.mTime = mTime;
	}

	public BigDecimal getmPrice() {
		return mPrice;
	}

	public void setmPrice(BigDecimal mPrice) {
		this.mPrice = mPrice;
	}

	
	
}