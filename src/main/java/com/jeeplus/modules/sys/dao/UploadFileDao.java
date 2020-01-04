/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.dao;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.sys.entity.UploadFile;

/**
 * 机构DAO接口
 * @author jeeplus
 * @version 2014-05-16
 */
@MyBatisDao
public interface UploadFileDao extends CrudDao<UploadFile> {
	
	public void insertAndReturnId(UploadFile uploadFile);
}
