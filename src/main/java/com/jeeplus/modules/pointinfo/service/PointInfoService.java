/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.pointinfo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.pointinfo.entity.PointInfo;
import com.jeeplus.modules.pointinfo.dao.PointInfoDao;

/**
 * 积分信息Service
 * @author wujianbing
 * @version 2019-05-14
 */
@Service
@Transactional(readOnly = true)
public class PointInfoService extends CrudService<PointInfoDao, PointInfo> {

	public PointInfo get(String id) {
		return super.get(id);
	}
	
	public List<PointInfo> findList(PointInfo pointInfo) {
		return super.findList(pointInfo);
	}
	
	public Page<PointInfo> findPage(Page<PointInfo> page, PointInfo pointInfo) {
		return super.findPage(page, pointInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(PointInfo pointInfo) {
		super.save(pointInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(PointInfo pointInfo) {
		super.delete(pointInfo);
	}
	
	
	
	
}