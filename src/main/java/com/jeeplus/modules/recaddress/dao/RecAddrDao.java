/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.recaddress.dao;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.recaddress.entity.RecAddr;

/**
 * 收货地址DAO接口
 * @author wujianbing
 * @version 2019-04-09
 */
@MyBatisDao
public interface RecAddrDao extends CrudDao<RecAddr> {

	RecAddr findAddrFirst(RecAddr recAddress);

	
}