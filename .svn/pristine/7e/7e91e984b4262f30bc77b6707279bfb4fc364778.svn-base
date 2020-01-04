/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhgroups.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.xhgroups.entity.XhGroups;
import com.jeeplus.modules.xhgroups.dao.XhGroupsDao;

/**
 * 团购模块Service
 * @author wujianbing
 * @version 2019-04-15
 */
@Service
@Transactional(readOnly = true)
public class XhGroupsService extends CrudService<XhGroupsDao, XhGroups> {
	@Autowired
	private XhGroupsDao xhGroupsDao;
	public XhGroups get(String id) {
		return super.get(id);
	}
	
	public List<XhGroups> findList(XhGroups xhGroups) {
		return super.findList(xhGroups);
	}
	
	public Page<XhGroups> findPage(Page<XhGroups> page, XhGroups xhGroups) {
		return super.findPage(page, xhGroups);
	}
	
	@Transactional(readOnly = false)
	public void save(XhGroups xhGroups) {
		super.save(xhGroups);
	}
	
	@Transactional(readOnly = false)
	public void delete(XhGroups xhGroups) {
		super.delete(xhGroups);
	}
	
	public List<XhGroups> findTwoGroups(){
		return xhGroupsDao.findTwoGroups(); 
	}
	@Transactional(readOnly = false)
	public void updateStatus(XhGroups xhGroups){
		xhGroupsDao.updateStatus(xhGroups);
	}

	public XhGroups getInfoByGid(String gid) {
		// TODO Auto-generated method stub
		return xhGroupsDao.getInfoByGid(gid);
	}
	
}