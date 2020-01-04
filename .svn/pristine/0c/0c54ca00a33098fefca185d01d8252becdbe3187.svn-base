/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhwxmsg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.xhwxmsg.entity.XhWechatmsg;
import com.jeeplus.modules.xhwxmsg.dao.XhWechatmsgDao;

/**
 * 微信支付配置信息Service
 * @author lk
 * @version 2019-05-06
 */
@Service
@Transactional(readOnly = true)
public class XhWechatmsgService extends CrudService<XhWechatmsgDao, XhWechatmsg> {

	@Autowired
	XhWechatmsgDao xhWechatmsgDao;
	
	public XhWechatmsg get(String id) {
		return super.get(id);
	}
	
	public List<XhWechatmsg> findList(XhWechatmsg xhWechatmsg) {
		return super.findList(xhWechatmsg);
	}
	
	
	public Page<XhWechatmsg> findPage(Page<XhWechatmsg> page, XhWechatmsg xhWechatmsg) {
		return super.findPage(page, xhWechatmsg);
	}
	
	@Transactional(readOnly = false)
	public void save(XhWechatmsg xhWechatmsg) {
		super.save(xhWechatmsg);
	}
	
	@Transactional(readOnly = false)
	public void delete(XhWechatmsg xhWechatmsg) {
		super.delete(xhWechatmsg);
	}
	
	
	
	
}