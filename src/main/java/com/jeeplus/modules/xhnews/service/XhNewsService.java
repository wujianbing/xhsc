/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhnews.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.modules.xhcolumn.entity.XhColumn;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.xhnews.entity.XhNews;
import com.jeeplus.modules.xhnews.dao.XhNewsDao;

/**
 * 鲜花资讯Service
 * @author wujianbing
 * @version 2019-04-09
 */
@Service
@Transactional(readOnly = true)
public class XhNewsService extends CrudService<XhNewsDao, XhNews> {

	public XhNews get(String id) {
		return super.get(id);
	}
	
	public List<XhNews> findList(XhNews xhNews) {
		return super.findList(xhNews);
	}
	
	public Page<XhNews> findPage(Page<XhNews> page, XhNews xhNews) {
		return super.findPage(page, xhNews);
	}
	
	@Transactional(readOnly = false)
	public void save(XhNews xhNews) {
		super.save(xhNews);
	}
	
	@Transactional(readOnly = false)
	public void delete(XhNews xhNews) {
		super.delete(xhNews);
	}
	
	public Page<XhColumn> findPageByxhColumn(Page<XhColumn> page, XhColumn xhColumn) {
		xhColumn.setPage(page);
		page.setList(dao.findListByxhColumn(xhColumn));
		return page;
	}
	
	
	
}