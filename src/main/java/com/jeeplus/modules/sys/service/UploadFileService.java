/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.sys.dao.UploadFileDao;
import com.jeeplus.modules.sys.entity.UploadFile;

/**
 * 机构Service
 * @author MrLi
 * @version 2017-06-05
 */
@Service
@Transactional(readOnly = false)
public class UploadFileService extends CrudService<UploadFileDao, UploadFile> {
	
	public UploadFile get(String id) {
		return super.get(id);
	}

	@Transactional(readOnly = false)
	public void saveAndReturnId(UploadFile uploadFile) {
		dao.insertAndReturnId(uploadFile);
	}
	
	@Transactional(readOnly = false)
	public void delete(UploadFile uploadFile) {
		super.delete(uploadFile);
	}
}
