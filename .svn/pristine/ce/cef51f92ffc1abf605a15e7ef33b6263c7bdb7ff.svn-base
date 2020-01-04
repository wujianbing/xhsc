/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.pointgoods.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.pointgoods.entity.PointGoods;
import com.jeeplus.modules.pointgoods.dao.PointGoodsDao;

/**
 * 积分商城Service
 * @author wujianbing
 * @version 2019-05-14
 */
@Service
@Transactional(readOnly = true)
public class PointGoodsService extends CrudService<PointGoodsDao, PointGoods> {

	public PointGoods get(String id) {
		return super.get(id);
	}
	
	public List<PointGoods> findList(PointGoods pointGoods) {
		return super.findList(pointGoods);
	}
	
	public Page<PointGoods> findPage(Page<PointGoods> page, PointGoods pointGoods) {
		return super.findPage(page, pointGoods);
	}
	
	@Transactional(readOnly = false)
	public void save(PointGoods pointGoods) {
		super.save(pointGoods);
	}
	
	@Transactional(readOnly = false)
	public void delete(PointGoods pointGoods) {
		super.delete(pointGoods);
	}
	
	
	
	
}