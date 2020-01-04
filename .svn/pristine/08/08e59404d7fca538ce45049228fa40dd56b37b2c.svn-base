/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.monthtime.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.monthtime.entity.MonthTime;
import com.jeeplus.modules.xhmonthbuy.dao.XhMonthbuyDao;
import com.jeeplus.modules.monthtime.dao.MonthTimeDao;

/**
 * 包月时间设定Service
 * @author wujianbing
 * @version 2019-04-17
 */
@Service
@Transactional(readOnly = true)
public class MonthTimeService extends CrudService<MonthTimeDao, MonthTime> {
	
	@Autowired
	private MonthTimeDao monthTimeDao;
	public MonthTime get(String id) {
		return super.get(id);
	}
	
	public List<MonthTime> findList(MonthTime monthTime) {
		return super.findList(monthTime);
	}
	
	public Page<MonthTime> findPage(Page<MonthTime> page, MonthTime monthTime) {
		return super.findPage(page, monthTime);
	}
	
	@Transactional(readOnly = false)
	public void save(MonthTime monthTime) {
		super.save(monthTime);
	}
	
	@Transactional(readOnly = false)
	public void delete(MonthTime monthTime) {
		super.delete(monthTime);
	}

	public List<MonthTime> findMonthTimeByMid(String mid) {
		return monthTimeDao.findMonthTimeByMid(mid);
	}
	
	
	
	
}