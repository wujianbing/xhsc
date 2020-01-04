/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhfloor.entity;

import org.hibernate.validator.constraints.Length;
import com.jeeplus.modules.sys.entity.UploadFile;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 楼层选择Entity
 * @author wujianbing
 * @version 2019-04-18
 */
public class XhFloor extends DataEntity<XhFloor> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 楼层名称
	private UploadFile uploadFile;		// 楼层图片
	private String url;		// 链接地址
	private String floorNum;		// 楼层序号
	
	public XhFloor() {
		super();
	}

	public XhFloor(String id){
		super(id);
	}

	@Length(min=0, max=64, message="楼层名称长度必须介于 0 和 64 之间")
	@ExcelField(title="楼层名称", align=2, sort=7)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="楼层图片", align=2, sort=8)
	public UploadFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	@Length(min=0, max=64, message="链接地址长度必须介于 0 和 64 之间")
	@ExcelField(title="链接地址", align=2, sort=9)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=64, message="楼层序号长度必须介于 0 和 64 之间")
	@ExcelField(title="楼层序号", dictType="floorNum", align=2, sort=10)
	public String getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
	}
	
}