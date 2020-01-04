/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhshoper.dao;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.xhshoper.entity.XhShopcar;

/**
 * 购物车DAO接口
 * @author wujianbing
 * @version 2019-04-28
 */
@MyBatisDao
public interface XhShopcarDao extends CrudDao<XhShopcar> {

	void updateNumById(String num,String id);

	
}