/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.recaddress.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.recaddress.entity.RecAddr;
import com.jeeplus.modules.recaddress.dao.RecAddrDao;

/**
 * 收货地址Service
 * @author wujianbing
 * @version 2019-04-09
 */
@Service
@Transactional(readOnly = true)
public class RecAddrService extends CrudService<RecAddrDao, RecAddr> {
	@Autowired
	private RecAddrDao recAddrDao;
	public RecAddr get(String id) {
		return super.get(id);
	}
	
	public List<RecAddr> findList(RecAddr recAddr) {
		return super.findList(recAddr);
	}
	
	public Page<RecAddr> findPage(Page<RecAddr> page, RecAddr recAddr) {
		return super.findPage(page, recAddr);
	}
	
	@Transactional(readOnly = false)
	public void save(RecAddr recAddr) {
		super.save(recAddr);
	}
	
	@Transactional(readOnly = false)
	public void delete(RecAddr recAddr) {
		super.delete(recAddr);
	}

	public RecAddr findAddrFirst(RecAddr recAddress) {
		// TODO Auto-generated method stub
		return recAddrDao.findAddrFirst(recAddress);
	}
	
	
	
	
}