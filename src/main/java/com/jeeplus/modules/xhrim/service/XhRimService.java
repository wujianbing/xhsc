/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhrim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.xhrim.entity.XhRim;
import com.jeeplus.modules.xhrim.dao.XhRimDao;

/**
 * 商品周边Service
 * @author wujianbing
 * @version 2019-04-24
 */
@Service
@Transactional(readOnly = true)
public class XhRimService extends CrudService<XhRimDao, XhRim> {
	
	@Autowired
	private XhRimDao xhRimDao;
	public XhRim get(String id) {
		return super.get(id);
	}
	
	public List<XhRim> findList(XhRim xhRim) {
		return super.findList(xhRim);
	}
	
	public Page<XhRim> findPage(Page<XhRim> page, XhRim xhRim) {
		return super.findPage(page, xhRim);
	}
	
	@Transactional(readOnly = false)
	public void save(XhRim xhRim) {
		super.save(xhRim);
	}
	
	@Transactional(readOnly = false)
	public void delete(XhRim xhRim) {
		super.delete(xhRim);
	}

	public List<XhRim> findListBygid(String gid) {
		// TODO Auto-generated method stub
		return xhRimDao.findListBygid(gid);
	}
	
	
	
	
}