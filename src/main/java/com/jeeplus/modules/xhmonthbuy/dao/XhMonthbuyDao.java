/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhmonthbuy.dao;

import java.util.List;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.monthtime.entity.MonthTime;
import com.jeeplus.modules.xhmonthbuy.entity.XhMonthbuy;

/**
 * 包月商品DAO接口
 * @author wujianbing
 * @version 2019-04-17
 */
@MyBatisDao
public interface XhMonthbuyDao extends CrudDao<XhMonthbuy> {

	List<XhMonthbuy> findTwoMonthbuy();

}