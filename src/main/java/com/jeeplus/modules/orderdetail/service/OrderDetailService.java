/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.orderdetail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.orderdetail.entity.OrderDetail;
import com.jeeplus.modules.orderdetail.dao.OrderDetailDao;

/**
 * 订单详情Service
 * @author wujianbing
 * @version 2019-04-29
 */
@Service
@Transactional(readOnly = true)
public class OrderDetailService extends CrudService<OrderDetailDao, OrderDetail> {
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	public OrderDetail get(String id) {
		return super.get(id);
	}
	
	public List<OrderDetail> findList(OrderDetail orderDetail) {
		return super.findList(orderDetail);
	}
	
	public Page<OrderDetail> findPage(Page<OrderDetail> page, OrderDetail orderDetail) {
		return super.findPage(page, orderDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderDetail orderDetail) {
		super.save(orderDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderDetail orderDetail) {
		super.delete(orderDetail);
	}

	public int getCountByGroupNo(String groupNo) {
		// TODO Auto-generated method stub
		return orderDetailDao.getCountByGroupNo(groupNo);
	}

	public String findNewGroupNo() {
		// TODO Auto-generated method stub
		return orderDetailDao.findNewGroupNo();
	}
	@Transactional(readOnly = false)
	public void changeTimesByOid(int times, String oid) {
		orderDetailDao.changeTimesByOid(times,oid);
	}
	
	
	
	
}