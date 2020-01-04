/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.pointgoods.entity;

import org.hibernate.validator.constraints.Length;
import com.jeeplus.modules.sys.entity.UploadFile;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 积分商城Entity
 * @author wujianbing
 * @version 2019-05-14
 */
public class PointGoods extends DataEntity<PointGoods> {
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;
	private String pname;		// 商品名称
	private String ppoint;		// 所需积分
	private UploadFile uploadFile;		// 商品图
	
	public PointGoods() {
		super();
	}

	public PointGoods(String id){
		super(id);
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPpoint() {
		return ppoint;
	}

	public void setPpoint(String ppoint) {
		this.ppoint = ppoint;
	}

	@ExcelField(title="商品图", align=2, sort=9)
	public UploadFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
}