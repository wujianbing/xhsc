/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhwxmsg.entity;

import org.hibernate.validator.constraints.Length;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 微信支付配置信息Entity
 * @author lk
 * @version 2019-05-06
 */
public class XhWechatmsg extends DataEntity<XhWechatmsg> {
	
	private static final long serialVersionUID = 1L;
	private String appid;		// appid
	private String secret;		// 秘钥
	private String mchId;		// 商户ID
	private String ketId;		// KEY
	
	public XhWechatmsg() {
		super();
	}

	public XhWechatmsg(String id){
		super(id);
	}

	@Length(min=0, max=255, message="appid长度必须介于 0 和 255 之间")
	@ExcelField(title="appid", align=2, sort=1)
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	@Length(min=0, max=255, message="秘钥长度必须介于 0 和 255 之间")
	@ExcelField(title="秘钥", align=2, sort=2)
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	@Length(min=0, max=255, message="商户ID长度必须介于 0 和 255 之间")
	@ExcelField(title="商户ID", align=2, sort=3)
	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	
	@Length(min=0, max=255, message="KEY长度必须介于 0 和 255 之间")
	@ExcelField(title="KEY", align=2, sort=4)
	public String getKetId() {
		return ketId;
	}

	public void setKetId(String ketId) {
		this.ketId = ketId;
	}
	
}