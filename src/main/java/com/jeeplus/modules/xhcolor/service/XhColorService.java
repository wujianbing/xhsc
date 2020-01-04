/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhcolor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.xhcolor.entity.XhColor;
import com.jeeplus.modules.xhcolor.dao.XhColorDao;

/**
 * 商品颜色Service
 * @author wujianbing
 * @version 2019-04-23
 */
@Service
@Transactional(readOnly = true)
public class XhColorService extends CrudService<XhColorDao, XhColor> {
	
	@Autowired
	private XhColorDao xhColorDao;
	public XhColor get(String id) {
		return super.get(id);
	}
	
	public List<XhColor> findList(XhColor xhColor) {
		return super.findList(xhColor);
	}
	
	public Page<XhColor> findPage(Page<XhColor> page, XhColor xhColor) {
		return super.findPage(page, xhColor);
	}
	
	@Transactional(readOnly = false)
	public void save(XhColor xhColor) {
		super.save(xhColor);
	}
	
	@Transactional(readOnly = false)
	public void delete(XhColor xhColor) {
		super.delete(xhColor);
	}

	public List<XhColor> findListBygid(String gid) {
		// TODO Auto-generated method stub
		return xhColorDao.findListBygid(gid);
	}
	
	
	
	
}