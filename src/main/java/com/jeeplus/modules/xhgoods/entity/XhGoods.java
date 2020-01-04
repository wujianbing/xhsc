/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhgoods.entity;

import org.springframework.web.util.HtmlUtils;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;
import com.jeeplus.modules.sys.entity.UploadFile;
import com.jeeplus.modules.xhcategory.entity.XhCategory;

/**
 * 商品管理Entity
 * @author wujianbing
 * @version 2019-04-04
 */
public class XhGoods extends DataEntity<XhGoods> {
	
	private static final long serialVersionUID = 1L;
	private XhCategory xhCategory;		// 商品种类
	private String name;		// 商品名称
	private String initial;		// 英文缩写
	private String title;		// 鲜花物语
	private String unit;		// 商品单位
	private String bigUnit;		// 商品大单位
	private String price;		// 商品价格
	private String maxPrice;
	private String status;		// 商品上下架（0：上架 1：下架）
	private String description;		// 商品介绍
	private UploadFile uploadFile;
	private UploadFile uploadFile1;
	private UploadFile uploadFile2;
	private UploadFile uploadFile3;
	private UploadFile uploadFile4;
	private UploadFile uploadFile5;
	private String special; //是否特卖状态
	private String xhInventory; //库存
	private String integral; //积分
	public XhGoods() {
		super();
	}

	public XhGoods(String id){
		super(id);
	}

	@ExcelField(title="商品种类", align=2, sort=7)
	public XhCategory getXhCategory() {
		return xhCategory;
	}

	public void setXhCategory(XhCategory xhCategory) {
		this.xhCategory = xhCategory;
	}
	
	@ExcelField(title="商品名称", align=2, sort=8)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="英文缩写", align=2, sort=9)
	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ExcelField(title="商品单位", dictType="unit", align=2, sort=11)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@ExcelField(title="商品大单位", dictType="bigunit", align=2, sort=12)
	public String getBigUnit() {
		return bigUnit;
	}

	public void setBigUnit(String bigUnit) {
		this.bigUnit = bigUnit;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	@ExcelField(title="商品上下架（0：上架 1：下架）", dictType="xh_status", align=2, sort=14)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@ExcelField(title="商品介绍", align=2, sort=15)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description =HtmlUtils.htmlUnescape(description);
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getXhInventory() {
		return xhInventory;
	}

	public void setXhInventory(String xhInventory) {
		this.xhInventory = xhInventory;
	}

	public UploadFile getUploadFile() {
		return uploadFile;
	}

	public UploadFile getUploadFile1() {
		return uploadFile1;
	}

	public void setUploadFile1(UploadFile uploadFile1) {
		this.uploadFile1 = uploadFile1;
	}

	public UploadFile getUploadFile2() {
		return uploadFile2;
	}

	public void setUploadFile2(UploadFile uploadFile2) {
		this.uploadFile2 = uploadFile2;
	}

	public UploadFile getUploadFile3() {
		return uploadFile3;
	}

	public void setUploadFile3(UploadFile uploadFile3) {
		this.uploadFile3 = uploadFile3;
	}

	public UploadFile getUploadFile4() {
		return uploadFile4;
	}

	public void setUploadFile4(UploadFile uploadFile4) {
		this.uploadFile4 = uploadFile4;
	}

	public UploadFile getUploadFile5() {
		return uploadFile5;
	}

	public void setUploadFile5(UploadFile uploadFile5) {
		this.uploadFile5 = uploadFile5;
	}

	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}
}