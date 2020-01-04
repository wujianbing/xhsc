/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhbuyer.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.xhbuyer.entity.XhBuyer;

/**
 * 团购详情DAO接口
 * @author wujianbing
 * @version 2019-04-15
 */
@MyBatisDao
public interface XhBuyerDao extends CrudDao<XhBuyer> {

	List<XhBuyer> findListByGroupId(String groupId);

	XhBuyer findInfoByGroupNo(String groupNo);

	void updateStatusByGroupNo(String groupNo, String gbStatus);

	void updateHasNameByGroupNo(String hasNum ,String groupNo);

	
}