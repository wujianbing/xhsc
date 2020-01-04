package com.jeeplus.modules.xhreception.xhController;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeeplus.modules.monthtime.entity.MonthTime;
import com.jeeplus.modules.monthtime.service.MonthTimeService;
import com.jeeplus.modules.recaddress.entity.RecAddr;
import com.jeeplus.modules.recaddress.service.RecAddrService;
import com.jeeplus.modules.xhcategory.entity.XhCategory;
import com.jeeplus.modules.xhcolor.entity.XhColor;
import com.jeeplus.modules.xhcolor.service.XhColorService;
import com.jeeplus.modules.xhfloor.entity.XhFloor;
import com.jeeplus.modules.xhfloor.service.XhFloorService;
import com.jeeplus.modules.xhgoods.entity.XhGoods;
import com.jeeplus.modules.xhgoods.service.XhGoodsService;
import com.jeeplus.modules.xhmonthbuy.entity.XhMonthbuy;
import com.jeeplus.modules.xhmonthbuy.service.XhMonthbuyService;
import com.jeeplus.modules.xhreception.domain.NextLoad;
import com.jeeplus.modules.xhrim.entity.XhRim;
import com.jeeplus.modules.xhrim.service.XhRimService;
import com.jeeplus.modules.xhrule.entity.XhRule;
import com.jeeplus.modules.xhrule.service.XhRuleService;
import com.jeeplus.modules.xhtime.entity.XhTime;
import com.jeeplus.modules.xhtime.service.XhTimeService;
import com.jeeplus.modules.xhuser.entity.XhUser;
import com.jeeplus.modules.xhuser.service.XhUserService;

@Controller
public class MonthController {
	
	@Autowired
	private XhGoodsService xhGoodsService;
	@Autowired
	private XhFloorService xhFloorService;
	@Autowired
	private XhRuleService xhRuleService;
	@Autowired
	private XhRimService xhRimService;
	@Autowired
	private XhTimeService xhTimeService;
	@Autowired
	private XhColorService xhColorService;
	@Autowired
	private RecAddrService recAddrService;
	@Autowired
	private MonthTimeService monthTimeService;
	@Autowired
	private XhMonthbuyService xhMonthbuyService;
	@Autowired
	private XhUserService xhUserService;
	@RequestMapping("monthbuy")
	public String monthList(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 6以后
		List<XhFloor> xhFloors = xhFloorService.findListByNum();
		model.addAttribute("xhFloors", xhFloors);
		XhMonthbuy xhMonthbuy = new XhMonthbuy();
		xhMonthbuy.setmStatus("1");
		List<XhMonthbuy> xhMonthbuys = xhMonthbuyService.findList(xhMonthbuy);
		model.addAttribute("xhMonthbuys", xhMonthbuys);
		return "modules/xhreception/monthbuy";
	}
	
	/**
	 * 包月详情
	 */
	@RequestMapping("monthDetail")
	public String itemDetail(HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session){
		session.removeAttribute("nextLoad");
		String mid = request.getParameter("id");
		String gid = request.getParameter("gid");
		session.setAttribute("mid", mid);
		session.setAttribute("gid", gid);
		
		XhMonthbuy xhMonthbuy = xhMonthbuyService.get(mid);
		model.addAttribute("xhMonthbuy", xhMonthbuy);
		
		List<MonthTime> monthTime = monthTimeService.findMonthTimeByMid(mid);
		model.addAttribute("monthTime", monthTime);
		
		XhGoods xhGoods = xhGoodsService.get(gid);
		model.addAttribute("xhGoods", xhGoods);
		
		List<XhRule> xhRules = xhRuleService.findRuleBygid(gid);
		model.addAttribute("xhRules", xhRules);
		
		List<XhTime> xhTimes = xhTimeService.findListBygid(gid);
		model.addAttribute("xhTimes", xhTimes);
		
		List<XhColor> xhColors = xhColorService.findListBygid(gid);
		model.addAttribute("xhColors", xhColors);
		
		List<XhRim> xhRims = xhRimService.findListBygid(gid);
		model.addAttribute("xhRims", xhRims);
		
		return "modules/xhreception/monthDetail";
	}
	
	@RequestMapping("monthNextLoad")
	public String monthNextLoad(HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session){
		String openId = (String)session.getAttribute("openId");
		NextLoad nextLoad = new NextLoad();
		NextLoad nextLoad1 =(NextLoad)session.getAttribute("nextLoad");
		if(nextLoad1 != null){
			nextLoad = nextLoad1;
			model.addAttribute("nextLoad", nextLoad);
			String gid = nextLoad.getGid();
			XhGoods xhGoods = xhGoodsService.get(gid);
			model.addAttribute("xhGoods", xhGoods);
			String uid =xhUserService.findIdByOpenId(openId);
			String recid = request.getParameter("recid");
			RecAddr recAddress = new RecAddr();
			XhUser xhUser = new XhUser();
			xhUser.setId(uid);
			recAddress.setXhUser(xhUser);
			recAddress.setId(recid);
			RecAddr recAddr = recAddrService.findAddrFirst(recAddress);
			model.addAttribute("recAddr", recAddr);
		}else{
			String uid =xhUserService.findIdByOpenId(openId);
			String gid = request.getParameter("gid");
			String rid = request.getParameter("rid");
			String sid = request.getParameter("sid");
			String tid = request.getParameter("tid");
			String mid = request.getParameter("mid");
			String num =request.getParameter("num");
			
			MonthTime monthTime = monthTimeService.get(mid);
			BigDecimal num1 = new BigDecimal(num);
			BigDecimal price1 = monthTime.getmPrice();
			BigDecimal amount1=price1.multiply(num1);
			String price = price1.toString();
			String amount = amount1.toString();
			
			nextLoad.setNum(num);
			nextLoad.setPrice(price);
			nextLoad.setAmount(amount);
			nextLoad.setGid(gid);
			nextLoad.setRid(rid);
			nextLoad.setSid(sid);
			nextLoad.setTid(tid);
			nextLoad.setMid(mid);
			nextLoad.setStatus("2");
			model.addAttribute("nextLoad", nextLoad);
			XhGoods xhGoods = xhGoodsService.get(gid);
			model.addAttribute("xhGoods", xhGoods);
			
			RecAddr recAddress = new RecAddr();
			XhUser xhUser = new XhUser();
			xhUser.setId(uid);
			recAddress.setXhUser(xhUser);
			RecAddr recAddr = recAddrService.findAddrFirst(recAddress);
			model.addAttribute("recAddr", recAddr);
			session.setAttribute("nextLoad", nextLoad);
		}
		
		return "modules/xhreception/monthNextLoad";
	}
}
