/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhfloor.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.xhfloor.entity.XhFloor;

/**
 * 楼层选择DAO接口
 * @author wujianbing
 * @version 2019-04-18
 */
@MyBatisDao
public interface XhFloorDao extends CrudDao<XhFloor> {

	XhFloor findFloorByNum(XhFloor xhFloor);

	List<XhFloor> findListByNum();
}