/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhrim.entity;

import com.jeeplus.modules.sys.entity.UploadFile;
import com.jeeplus.modules.xhgoods.entity.XhGoods;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 商品周边Entity
 * @author wujianbing
 * @version 2019-04-24
 */
public class XhRim extends DataEntity<XhRim> {
	
	private static final long serialVersionUID = 1L;
	private XhGoods xhGoods;		// 商品信息
	private String rimName;		// 周边商品名
	private BigDecimal rimPrice;		// 周边价格
	private UploadFile uploadFile;		// 周边图片
	
	public XhRim() {
		super();
	}

	public XhRim(String id){
		super(id);
	}

	@ExcelField(title="商品信息", dictType="", align=2, sort=7)
	public XhGoods getXhGoods() {
		return xhGoods;
	}

	public void setXhGoods(XhGoods xhGoods) {
		this.xhGoods = xhGoods;
	}
	
	@Length(min=0, max=64, message="周边商品名长度必须介于 0 和 64 之间")
	@ExcelField(title="周边商品名", align=2, sort=8)
	public String getRimName() {
		return rimName;
	}

	public void setRimName(String rimName) {
		this.rimName = rimName;
	}
	
	public BigDecimal getRimPrice() {
		return rimPrice;
	}

	public void setRimPrice(BigDecimal rimPrice) {
		this.rimPrice = rimPrice;
	}

	public UploadFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}

}