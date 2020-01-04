/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhcategory.dao;

import com.jeeplus.common.persistence.TreeDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.xhcategory.entity.XhCategory;

/**
 * 商品分类DAO接口
 * @author wujianbing
 * @version 2019-04-03
 */
@MyBatisDao
public interface XhCategoryDao extends TreeDao<XhCategory> {
	
}