/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.recaddress.entity;

import org.hibernate.validator.constraints.Length;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;
import com.jeeplus.modules.xhuser.entity.XhUser;

/**
 * 收货地址Entity
 * @author wujianbing
 * @version 2019-04-09
 */
public class RecAddr extends DataEntity<RecAddr> {
	
	private static final long serialVersionUID = 1L;
	private XhUser xhUser;		// 用户信息
	private String province;		// 省
	private String city;		// 市
	private String area;		// 区
	private String address;		// 详细地址
	private String recUser;		// 收件人
	private String recPhone;		// 收件人电话
	
	public RecAddr() {
		super();
	}

	public RecAddr(String id){
		super(id);
	}

	public XhUser getXhUser() {
		return xhUser;
	}

	public void setXhUser(XhUser xhUser) {
		this.xhUser = xhUser;
	}

	@Length(min=0, max=64, message="省长度必须介于 0 和 64 之间")
	@ExcelField(title="省", align=2, sort=8)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=64, message="市长度必须介于 0 和 64 之间")
	@ExcelField(title="市", align=2, sort=9)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=64, message="区长度必须介于 0 和 64 之间")
	@ExcelField(title="区", align=2, sort=10)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=64, message="详细地址长度必须介于 0 和 64 之间")
	@ExcelField(title="详细地址", align=2, sort=11)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRecUser() {
		return recUser;
	}

	public void setRecUser(String recUser) {
		this.recUser = recUser;
	}

	public String getRecPhone() {
		return recPhone;
	}

	public void setRecPhone(String recPhone) {
		this.recPhone = recPhone;
	}
	
	
	
}