/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhrule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.xhrule.entity.XhRule;
import com.jeeplus.modules.xhrule.dao.XhRuleDao;

/**
 * 商品规格Service
 * @author wujianbing
 * @version 2019-04-15
 */
@Service
@Transactional(readOnly = true)
public class XhRuleService extends CrudService<XhRuleDao, XhRule> {
	@Autowired
	private XhRuleDao xhRuleDao;
	public XhRule get(String id) {
		return super.get(id);
	}
	
	public List<XhRule> findList(XhRule xhRule) {
		return super.findList(xhRule);
	}
	
	public Page<XhRule> findPage(Page<XhRule> page, XhRule xhRule) {
		return super.findPage(page, xhRule);
	}
	
	@Transactional(readOnly = false)
	public void save(XhRule xhRule) {
		super.save(xhRule);
	}
	
	@Transactional(readOnly = false)
	public void delete(XhRule xhRule) {
		super.delete(xhRule);
	}

	public List<XhRule> findRuleBygid(String gid) {
		
		return xhRuleDao.findRuleBygid(gid);
	}
	
	
	
	
}