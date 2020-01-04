/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhbuyer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.xhbuyer.entity.XhBuyer;
import com.jeeplus.modules.xhbuyer.dao.XhBuyerDao;

/**
 * 团购详情Service
 * @author wujianbing
 * @version 2019-04-15
 */
@Service
@Transactional(readOnly = true)
public class XhBuyerService extends CrudService<XhBuyerDao, XhBuyer> {
	
	@Autowired
	private XhBuyerDao xhBuyerDao;
	public XhBuyer get(String id) {
		return super.get(id);
	}
	
	public List<XhBuyer> findList(XhBuyer xhBuyer) {
		return super.findList(xhBuyer);
	}
	
	public Page<XhBuyer> findPage(Page<XhBuyer> page, XhBuyer xhBuyer) {
		return super.findPage(page, xhBuyer);
	}
	
	@Transactional(readOnly = false)
	public void save(XhBuyer xhBuyer) {
		super.save(xhBuyer);
	}
	
	@Transactional(readOnly = false)
	public void delete(XhBuyer xhBuyer) {
		super.delete(xhBuyer);
	}

	public List<XhBuyer> findListByGroupId(String groupId) {
		// TODO Auto-generated method stub
		return xhBuyerDao.findListByGroupId(groupId);
	}

	public XhBuyer findInfoByGroupNo(String groupNo) {
		// TODO Auto-generated method stub
		return xhBuyerDao.findInfoByGroupNo(groupNo);
	}
	@Transactional(readOnly = false)
	public void updateStatusByGroupNo(String groupNo, String gbStatus) {
		xhBuyerDao.updateStatusByGroupNo(groupNo,gbStatus);
		
	}
	@Transactional(readOnly = false)
	public void updateHasNameByGroupNo(String hasNum ,String groupNo) {
		xhBuyerDao.updateHasNameByGroupNo(hasNum,groupNo);
		
	}
	
	
	
	
}