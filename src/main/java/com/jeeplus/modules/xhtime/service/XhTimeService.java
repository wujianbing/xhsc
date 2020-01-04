/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhtime.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.xhtime.entity.XhTime;
import com.jeeplus.modules.xhtime.dao.XhTimeDao;

/**
 * 收花时间Service
 * @author wujianbing
 * @version 2019-04-23
 */
@Service
@Transactional(readOnly = true)
public class XhTimeService extends CrudService<XhTimeDao, XhTime> {
	
	@Autowired
	private XhTimeDao xhTimeDao;
	public XhTime get(String id) {
		return super.get(id);
	}
	
	public List<XhTime> findList(XhTime xhTime) {
		return super.findList(xhTime);
	}
	
	public Page<XhTime> findPage(Page<XhTime> page, XhTime xhTime) {
		return super.findPage(page, xhTime);
	}
	
	@Transactional(readOnly = false)
	public void save(XhTime xhTime) {
		super.save(xhTime);
	}
	
	@Transactional(readOnly = false)
	public void delete(XhTime xhTime) {
		super.delete(xhTime);
	}

	public List<XhTime> findListBygid(String gid) {
		// TODO Auto-generated method stub
		return xhTimeDao.findListBygid(gid);
	}
	
	
	
	
}