/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhfloor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.xhfloor.entity.XhFloor;
import com.jeeplus.modules.xhfloor.dao.XhFloorDao;

/**
 * 楼层选择Service
 * @author wujianbing
 * @version 2019-04-18
 */
@Service
@Transactional(readOnly = true)
public class XhFloorService extends CrudService<XhFloorDao, XhFloor> {
	
	@Autowired
	private XhFloorDao xhFloorDao;
	public XhFloor get(String id) {
		return super.get(id);
	}
	
	public List<XhFloor> findList(XhFloor xhFloor) {
		return super.findList(xhFloor);
	}
	
	public Page<XhFloor> findPage(Page<XhFloor> page, XhFloor xhFloor) {
		return super.findPage(page, xhFloor);
	}
	
	@Transactional(readOnly = false)
	public void save(XhFloor xhFloor) {
		super.save(xhFloor);
	}
	
	@Transactional(readOnly = false)
	public void delete(XhFloor xhFloor) {
		super.delete(xhFloor);
	}
	
	public XhFloor findFloorByNum(XhFloor xhFloor){
		return xhFloorDao.findFloorByNum(xhFloor);
	}
	
	public List<XhFloor> findListByNum(){
		return xhFloorDao.findListByNum();
	}
}