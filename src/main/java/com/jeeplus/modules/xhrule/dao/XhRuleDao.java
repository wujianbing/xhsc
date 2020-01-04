/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhrule.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.xhrule.entity.XhRule;

/**
 * 商品规格DAO接口
 * @author wujianbing
 * @version 2019-04-15
 */
@MyBatisDao
public interface XhRuleDao extends CrudDao<XhRule> {

	List<XhRule> findRuleBygid(String gid);

	
}