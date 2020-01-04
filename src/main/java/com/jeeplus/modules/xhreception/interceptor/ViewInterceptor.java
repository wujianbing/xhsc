package com.jeeplus.modules.xhreception.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jeeplus.modules.xhreception.pay.WxPayController;
/**
 * 登录拦截
 * @author Administrator
 *
 */
public class ViewInterceptor implements HandlerInterceptor {
	public static final Logger logger = LoggerFactory.getLogger(WxPayController.class);
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String openId = (String)request.getSession().getAttribute("openId");
		logger.debug("openId={}",openId);
		if(openId == null || "".equals(openId)){
			logger.debug("openId1={}",openId);
			 HttpSession session = request.getSession(true); 
             String uri = request.getRequestURI();//拿到上一个页面地址
             String path = uri.substring(request.getContextPath().length());//去掉项目地址长度的字符
             logger.info("path={}",path);
             String query = request.getQueryString();//得到参数
             if(query == null) {
                 query="";
             }
             else {
                 query="?"+query;
             }
             String beforePath = path+query;
             System.out.println(beforePath+"=====method"+request.getMethod());//测试用
             session.setAttribute("beforePath", beforePath);
             session.setAttribute("method", request.getMethod());//测试
             response.sendRedirect("wxpay/getCode");
             return false;
		}
		return true;
	}

}
