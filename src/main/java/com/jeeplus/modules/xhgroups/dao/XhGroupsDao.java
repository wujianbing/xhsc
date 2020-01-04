/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhgroups.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.xhgroups.entity.XhGroups;

/**
 * 团购模块DAO接口
 * @author wujianbing
 * @version 2019-04-15
 */
@MyBatisDao
public interface XhGroupsDao extends CrudDao<XhGroups> {

	List<XhGroups> findTwoGroups();
	
	void updateStatus(XhGroups xhGroups);

	XhGroups getInfoByGid(String gid);
}