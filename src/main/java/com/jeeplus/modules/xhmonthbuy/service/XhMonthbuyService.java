/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhmonthbuy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.xhmonthbuy.entity.XhMonthbuy;
import com.jeeplus.modules.xhmonthbuy.dao.XhMonthbuyDao;

/**
 * 包月商品Service
 * @author wujianbing
 * @version 2019-04-17
 */
@Service
@Transactional(readOnly = true)
public class XhMonthbuyService extends CrudService<XhMonthbuyDao, XhMonthbuy> {
	
	@Autowired
	private XhMonthbuyDao xhMonthbuyDao;
	public XhMonthbuy get(String id) {
		return super.get(id);
	}
	
	public List<XhMonthbuy> findList(XhMonthbuy xhMonthbuy) {
		return super.findList(xhMonthbuy);
	}
	
	public Page<XhMonthbuy> findPage(Page<XhMonthbuy> page, XhMonthbuy xhMonthbuy) {
		return super.findPage(page, xhMonthbuy);
	}
	
	@Transactional(readOnly = false)
	public void save(XhMonthbuy xhMonthbuy) {
		super.save(xhMonthbuy);
	}
	
	@Transactional(readOnly = false)
	public void delete(XhMonthbuy xhMonthbuy) {
		super.delete(xhMonthbuy);
	}
	public List<XhMonthbuy> findTwoMonthbuy(){
		return xhMonthbuyDao.findTwoMonthbuy();
	}
	
	
	
}