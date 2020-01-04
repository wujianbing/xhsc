/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhnews.entity;

import com.jeeplus.modules.xhcolumn.entity.XhColumn;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.util.HtmlUtils;

import com.jeeplus.modules.sys.entity.UploadFile;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 鲜花资讯Entity
 * @author wujianbing
 * @version 2019-04-09
 */
public class XhNews extends DataEntity<XhNews> {
	
	private static final long serialVersionUID = 1L;
	private XhColumn xhColumn;		// 栏目外键
	private String title;		// 标题
	private String content;		// 内容
	private UploadFile uploadFile;		// 图片
	
	public XhNews() {
		super();
	}

	public XhNews(String id){
		super(id);
	}

	@ExcelField(title="栏目", align=2, sort=7)
	public XhColumn getXhColumn() {
		return xhColumn;
	}

	public void setXhColumn(XhColumn xhColumn) {
		this.xhColumn = xhColumn;
	}
	
	@Length(min=0, max=64, message="标题长度必须介于 0 和 64 之间")
	@ExcelField(title="标题", align=2, sort=8)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=64, message="内容长度必须介于 0 和 64 之间")
	@ExcelField(title="内容", align=2, sort=9)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = HtmlUtils.htmlUnescape(content);
	}
	
	@ExcelField(title="图片", align=2, sort=10)
	public UploadFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
}