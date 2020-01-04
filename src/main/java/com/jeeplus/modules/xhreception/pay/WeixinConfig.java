package com.jeeplus.modules.xhreception.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;



public class WeixinConfig
{
  public static String access_token = "";
  public static String jsapi_ticket = "";
  public static final Logger LOGGER = LoggerFactory.getLogger(WeixinConfig.class);
  
  private static void getAccessToken()
  {
	 String aaaa ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}";
	 String url = aaaa.replace("{appid}",PayUtils.appid).replace("{secret}", PayUtils.secret);
    String result = HttpKit.get(url);
    LOGGER.info("result"+result);
    JSONObject object = JSON.parseObject(result);
    access_token = object.getString("access_token");
    LOGGER.info("刷新微信access_token:"+access_token);
  }
  
  public static void getJsapiTicket()
  {
    getAccessToken();
    String result = HttpKit.get("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi");
    JSONObject object = JSON.parseObject(result);
    jsapi_ticket = object.getString("ticket");
    LOGGER.info("刷新微信jsapi_token:"+jsapi_ticket);
  }
  
  public static void main(String[] args) {}
}
