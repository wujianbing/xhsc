/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhbanner.entity;

import com.jeeplus.modules.sys.entity.UploadFile;
import com.jeeplus.modules.xhgoods.entity.XhGoods;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 鲜花轮播图Entity
 * @author wujianbing
 * @version 2019-04-04
 */
public class XhBanner extends DataEntity<XhBanner> {
	
	private static final long serialVersionUID = 1L;
	private UploadFile uploadFile;		// 图片上传
	private XhGoods xhGoods;		// 商品链接
	private String url;
	public XhBanner() {
		super();
	}

	public XhBanner(String id){
		super(id);
	}

	@ExcelField(title="图片上传", align=2, sort=7)
	public UploadFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	@ExcelField(title="商品链接", dictType="", align=2, sort=8)
	public XhGoods getXhGoods() {
		return xhGoods;
	}

	public void setXhGoods(XhGoods xhGoods) {
		this.xhGoods = xhGoods;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}