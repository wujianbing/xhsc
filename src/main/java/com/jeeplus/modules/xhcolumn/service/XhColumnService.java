/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhcolumn.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.service.TreeService;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.xhcolumn.entity.XhColumn;
import com.jeeplus.modules.xhcolumn.dao.XhColumnDao;

/**
 * 鲜花栏目Service
 * @author wujianbing
 * @version 2019-04-08
 */
@Service
@Transactional(readOnly = true)
public class XhColumnService extends TreeService<XhColumnDao, XhColumn> {

	public XhColumn get(String id) {
		return super.get(id);
	}
	
	public List<XhColumn> findList(XhColumn xhColumn) {
		if (StringUtils.isNotBlank(xhColumn.getParentIds())){
			xhColumn.setParentIds(","+xhColumn.getParentIds()+",");
		}
		return super.findList(xhColumn);
	}
	
	@Transactional(readOnly = false)
	public void save(XhColumn xhColumn) {
		xhColumn.setSort(0);
		super.save(xhColumn);
	}
	
	@Transactional(readOnly = false)
	public void delete(XhColumn xhColumn) {
		super.delete(xhColumn);
	}
	
}