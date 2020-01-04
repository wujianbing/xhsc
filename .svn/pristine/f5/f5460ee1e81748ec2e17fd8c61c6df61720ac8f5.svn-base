/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhmbuyer.entity;

import com.jeeplus.modules.xhuser.entity.XhUser;
import com.jeeplus.modules.xhgoods.entity.XhGoods;
import com.jeeplus.modules.xhorder.entity.XhOrder;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 包月信息Entity
 * @author wujianbing
 * @version 2019-04-17
 */
public class XhMbuyer extends DataEntity<XhMbuyer> {
	
	private static final long serialVersionUID = 1L;
	private XhUser xhUser;		// 包月用户
	private XhGoods xhGoods;		// 包月商品
	private XhOrder xhOrder;		// 包月订单
	private String mtime;		// 包月周期
	private Date beginTime;		// 开始时间
	private Date endTime;		// 结束时间
	private String mstatus;		// 包月状态
	private int times;
	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public XhMbuyer() {
		super();
	}

	public XhMbuyer(String id){
		super(id);
	}

	@ExcelField(title="包月用户", align=2, sort=7)
	public XhUser getXhUser() {
		return xhUser;
	}

	public void setXhUser(XhUser xhUser) {
		this.xhUser = xhUser;
	}
	
	@ExcelField(title="包月商品", align=2, sort=8)
	public XhGoods getXhGoods() {
		return xhGoods;
	}

	public void setXhGoods(XhGoods xhGoods) {
		this.xhGoods = xhGoods;
	}
	
	@ExcelField(title="包月订单", align=2, sort=9)
	public XhOrder getXhOrder() {
		return xhOrder;
	}

	public void setXhOrder(XhOrder xhOrder) {
		this.xhOrder = xhOrder;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="开始时间", align=2, sort=11)
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="结束时间", align=2, sort=12)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}

	public String getMstatus() {
		return mstatus;
	}

	public void setMstatus(String mstatus) {
		this.mstatus = mstatus;
	}
	
	
}