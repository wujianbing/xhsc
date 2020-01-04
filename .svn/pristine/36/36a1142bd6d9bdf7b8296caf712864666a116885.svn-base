/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhbanner.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.xhbanner.entity.XhBanner;
import com.jeeplus.modules.xhbanner.dao.XhBannerDao;

/**
 * 鲜花轮播图Service
 * @author wujianbing
 * @version 2019-04-04
 */
@Service
@Transactional(readOnly = true)
public class XhBannerService extends CrudService<XhBannerDao, XhBanner> {

	public XhBanner get(String id) {
		return super.get(id);
	}
	
	public List<XhBanner> findList(XhBanner xhBanner) {
		return super.findList(xhBanner);
	}
	
	public Page<XhBanner> findPage(Page<XhBanner> page, XhBanner xhBanner) {
		return super.findPage(page, xhBanner);
	}
	
	@Transactional(readOnly = false)
	public void save(XhBanner xhBanner) {
		super.save(xhBanner);
	}
	
	@Transactional(readOnly = false)
	public void delete(XhBanner xhBanner) {
		super.delete(xhBanner);
	}
	
	
	
	
}