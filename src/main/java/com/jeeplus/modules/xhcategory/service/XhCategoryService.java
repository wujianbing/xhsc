/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhcategory.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.service.TreeService;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.xhcategory.entity.XhCategory;
import com.jeeplus.modules.xhcategory.dao.XhCategoryDao;

/**
 * 商品分类Service
 * @author wujianbing
 * @version 2019-04-03
 */
@Service
@Transactional(readOnly = true)
public class XhCategoryService extends TreeService<XhCategoryDao, XhCategory> {

	public XhCategory get(String id) {
		return super.get(id);
	}
	
	public List<XhCategory> findList(XhCategory xhCategory) {
		if (StringUtils.isNotBlank(xhCategory.getParentIds())){
			xhCategory.setParentIds(","+xhCategory.getParentIds()+",");
		}
		return super.findList(xhCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(XhCategory xhCategory) {
		super.save(xhCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(XhCategory xhCategory) {
		super.delete(xhCategory);
	}
	
}