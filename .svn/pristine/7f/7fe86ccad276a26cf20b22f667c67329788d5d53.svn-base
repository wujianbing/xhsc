/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhgroups.entity;

import com.jeeplus.modules.xhgoods.entity.XhGoods;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 团购模块Entity
 * @author wujianbing
 * @version 2019-04-15
 */
public class XhGroups extends DataEntity<XhGroups> {
	
	private static final long serialVersionUID = 1L;
	private XhGoods xhGoods;		// 商品
	private String groupNo;		// 团号
	private String groupTitle;		// 标题
	private String lTitle;         //小标题
	private String groupCycle;		// 团购周期
	private String groupArea;		// 区域
	private Date beginTime;		// 开团时间
	private Date endTime;		// 截止时间
	private String maxNum;		// 开团人数
	private BigDecimal buyerAmt;		// 团购金额
	private String groupStatus;		// 团购状态
	private String status;		// 状态
	private BigDecimal lPrice; //拼团最低价
	private BigDecimal maxPrice; // 拼团最高价
	public XhGroups() {
		super();
	}

	public XhGroups(String id){
		super(id);
	}

	@ExcelField(title="商品", dictType="", align=2, sort=6)
	public XhGoods getXhGoods() {
		return xhGoods;
	}

	public void setXhGoods(XhGoods xhGoods) {
		this.xhGoods = xhGoods;
	}
	
	@Length(min=0, max=64, message="团号长度必须介于 0 和 64 之间")
	@ExcelField(title="团号", align=2, sort=7)
	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	
	@Length(min=0, max=64, message="标题长度必须介于 0 和 64 之间")
	@ExcelField(title="标题", align=2, sort=8)
	public String getGroupTitle() {
		return groupTitle;
	}

	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}
	
	public String getlTitle() {
		return lTitle;
	}

	public void setlTitle(String lTitle) {
		this.lTitle = lTitle;
	}

	@ExcelField(title="周期", align=2, sort=9)
	public String getGroupCycle() {
		return groupCycle;
	}

	public void setGroupCycle(String groupCycle) {
		this.groupCycle = groupCycle;
	}

	@Length(min=0, max=64, message="区域长度必须介于 0 和 64 之间")
	@ExcelField(title="区域", align=2, sort=10)
	public String getGroupArea() {
		return groupArea;
	}

	public void setGroupArea(String groupArea) {
		this.groupArea = groupArea;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="开团时间", align=2, sort=11)
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="截止时间", align=2, sort=12)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=64, message="开团人数长度必须介于 0 和 64 之间")
	@ExcelField(title="开团人数", align=2, sort=13)
	public String getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(String maxNum) {
		this.maxNum = maxNum;
	}
	
	@ExcelField(title="团购金额", align=2, sort=14)
	public BigDecimal getBuyerAmt() {
		return buyerAmt;
	}

	public void setBuyerAmt(BigDecimal buyerAmt) {
		this.buyerAmt = buyerAmt;
	}

	@Length(min=0, max=64,message="团购状态长度必须介于 0 和 64 之间")
	@ExcelField(title="团购状态",dictType="groupStatus", align=2, sort=15)
	public String getGroupStatus() {
		return groupStatus;
	}

	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}
	
	@Length(min=0, max=64, message="状态长度必须介于 0 和 64 之间")
	@ExcelField(title="状态", align=2, sort=17)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getlPrice() {
		return lPrice;
	}

	public void setlPrice(BigDecimal lPrice) {
		this.lPrice = lPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	
}