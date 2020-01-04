/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhbuyer.entity;

import com.jeeplus.modules.xhuser.entity.XhUser;
import com.jeeplus.modules.xhgroups.entity.XhGroups;
import com.jeeplus.modules.xhgoods.entity.XhGoods;
import com.jeeplus.modules.xhorder.entity.XhOrder;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 团购详情Entity
 * @author wujianbing
 * @version 2019-04-15
 */
public class XhBuyer extends DataEntity<XhBuyer> {
	
	private static final long serialVersionUID = 1L;
	private String groupNo;     //已开团团号
	private XhUser xhUser;		// 团购人id
	private XhGroups xhGroups;		// 团购id
	private XhGoods xhGoods;		// 团购商品
	private XhOrder xhOrder;		// 订单
	private String gbNum;		// 团购数量
	private String gbPrice;		// 团购价格
	private String gbStatus;		// 团购状态
	private Date beginTime;		// 开团时间
	private Date endTime;		// 截止时间
	private String sTime;
	private String hasNum;    //已拼团人数
	public XhBuyer() {
		super();
	}

	public XhBuyer(String id){
		super(id);
	}

	@ExcelField(title="团购人id", align=2, sort=6)
	public XhUser getXhUser() {
		return xhUser;
	}

	public void setXhUser(XhUser xhUser) {
		this.xhUser = xhUser;
	}
	
	@ExcelField(title="团购id", align=2, sort=7)
	public XhGroups getXhGroups() {
		return xhGroups;
	}

	public void setXhGroups(XhGroups xhGroups) {
		this.xhGroups = xhGroups;
	}
	
	@ExcelField(title="团购商品", align=2, sort=8)
	public XhGoods getXhGoods() {
		return xhGoods;
	}

	public void setXhGoods(XhGoods xhGoods) {
		this.xhGoods = xhGoods;
	}
	
	@ExcelField(title="订单", align=2, sort=9)
	public XhOrder getXhOrder() {
		return xhOrder;
	}

	public void setXhOrder(XhOrder xhOrder) {
		this.xhOrder = xhOrder;
	}
	
	@Length(min=0, max=64, message="团购数量长度必须介于 0 和 64 之间")
	@ExcelField(title="团购数量", align=2, sort=10)
	public String getGbNum() {
		return gbNum;
	}

	public void setGbNum(String gbNum) {
		this.gbNum = gbNum;
	}
	
	@Length(min=0, max=64, message="团购价格长度必须介于 0 和 64 之间")
	@ExcelField(title="团购价格", align=2, sort=11)
	public String getGbPrice() {
		return gbPrice;
	}

	public void setGbPrice(String gbPrice) {
		this.gbPrice = gbPrice;
	}
	
	@Length(min=0, max=64, message="团购状态长度必须介于 0 和 64 之间")
	@ExcelField(title="团购状态",dictType="gb_status", align=2, sort=12)
	public String getGbStatus() {
		return gbStatus;
	}

	public void setGbStatus(String gbStatus) {
		this.gbStatus = gbStatus;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public String getsTime() {
		return sTime;
	}

	public void setsTime(String sTime) {
		this.sTime = sTime;
	}

	public String getHasNum() {
		return hasNum;
	}

	public void setHasNum(String hasNum) {
		this.hasNum = hasNum;
	}
	
}