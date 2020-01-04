/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.pointinfo.entity;

import com.jeeplus.modules.xhuser.entity.XhUser;
import org.hibernate.validator.constraints.Length;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 积分信息Entity
 * @author wujianbing
 * @version 2019-05-14
 */
public class PointInfo extends DataEntity<PointInfo> {
	
	private static final long serialVersionUID = 1L;
	private XhUser xhUser;		// 用户信息
	private String uName;		// 用户名称
	private String uPhone;		// 用户电话
	private String pName;		// 兑换商品
	private String pPoint;		// 所需积分
	
	public PointInfo() {
		super();
	}

	public PointInfo(String id){
		super(id);
	}

	@ExcelField(title="用户信息", align=2, sort=7)
	public XhUser getXhUser() {
		return xhUser;
	}

	public void setXhUser(XhUser xhUser) {
		this.xhUser = xhUser;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuPhone() {
		return uPhone;
	}

	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpPoint() {
		return pPoint;
	}

	public void setpPoint(String pPoint) {
		this.pPoint = pPoint;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

	
}