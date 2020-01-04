/**
 * 
 */
package com.jeeplus.modules.xhreception.pay;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;



/**
 * @author 
 * @description 
 *	
 */
public class WeixinUtil {
	public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={APPID}&secret={SECRET}&code={CODE}&grant_type=authorization_code";
	public static final String snsapi_userinfo = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	public static final Logger LOGGER = LoggerFactory.getLogger(WeixinUtil.class);
	
	public static JSONObject getAccessToken(String appid,String secret,String code){
		String url = GET_ACCESS_TOKEN_URL.replace("{APPID}", appid).replace("{SECRET}", secret).replace("{CODE}", code);
		String result = HttpKit.get(url);
		JSONObject authResult = JSONObject.fromObject(result);
		LOGGER.debug("authResult={}",authResult);
		return authResult;
	}
}
