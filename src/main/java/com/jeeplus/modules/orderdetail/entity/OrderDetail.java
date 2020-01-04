/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.orderdetail.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;
import com.jeeplus.modules.xhgoods.entity.XhGoods;
import com.jeeplus.modules.xhorder.entity.XhOrder;

/**
 * 订单详情Entity
 * @author wujianbing
 * @version 2019-04-29
 */
public class OrderDetail extends DataEntity<OrderDetail> {
	
	private static final long serialVersionUID = 1L;
	private XhOrder xhOrder;		// 订单
	private XhGoods xhGoods;		// 商品
	private String groupNo;        //团购号
	private String num;		// 商品数量
	private String price;		// 商品单价
	private String info;		// 商品详情
	private String gName;      //商品名称
	private String mTime;     //包月时间
	private Date endDate;    //包月结束时间
	private String status;    //商品类型
	private int times;
	public OrderDetail() {
		super();
	}

	public OrderDetail(String id){
		super(id);
	}

	@ExcelField(title="订单", align=2, sort=7)
	public XhOrder getXhOrder() {
		return xhOrder;
	}

	public void setXhOrder(XhOrder xhOrder) {
		this.xhOrder = xhOrder;
	}
	
	@ExcelField(title="商品", align=2, sort=8)
	public XhGoods getXhGoods() {
		return xhGoods;
	}

	public void setXhGoods(XhGoods xhGoods) {
		this.xhGoods = xhGoods;
	}
	
	@Length(min=0, max=64, message="商品数量长度必须介于 0 和 64 之间")
	@ExcelField(title="商品数量", align=2, sort=9)
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Length(min=0, max=64, message="商品单价长度必须介于 0 和 64 之间")
	@ExcelField(title="商品单价", align=2, sort=10)
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getmTime() {
		return mTime;
	}

	public void setmTime(String mTime) {
		this.mTime = mTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}
	
}