package com.jeeplus.modules.xhreception.xhController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeplus.modules.recaddress.entity.RecAddr;
import com.jeeplus.modules.recaddress.service.RecAddrService;
import com.jeeplus.modules.xhreception.domain.Message;
import com.jeeplus.modules.xhreception.domain.NextLoad;
import com.jeeplus.modules.xhreception.xhUtils.BaseUtils;
import com.jeeplus.modules.xhuser.entity.XhUser;
import com.jeeplus.modules.xhuser.service.XhUserService;

@Controller
public class AddressController {
	
	@Autowired
	private RecAddrService recAddrService;
	@Autowired
	private XhUserService xhUserService;
	@RequestMapping("addressEdit")
	public String addressEdit(HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session){
		String openId = (String)session.getAttribute("openId");
		String uid =xhUserService.findIdByOpenId(openId);
		XhUser xhUser = new XhUser();
		xhUser.setId(uid);
		RecAddr recAddr = new RecAddr();
		recAddr.setXhUser(xhUser);
		List<RecAddr> recAddres =recAddrService.findList(recAddr);
		model.addAttribute("recAddres", recAddres);
		NextLoad nextLoad = (NextLoad)session.getAttribute("nextLoad");
		model.addAttribute("nextLoad", nextLoad);
		return "modules/xhreception/addressEdit";
	}
	
	@RequestMapping("addAddr")
	public String addAddr(HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session){
		String openId = (String)session.getAttribute("openId");
		String uid =xhUserService.findIdByOpenId(openId);
		String province = "";
		String city = "";
		String area = "";
		String recUser = request.getParameter("recUser");
		String recPhone = request.getParameter("recPhone");
		String upAddr = request.getParameter("upAddr");
		String address = request.getParameter("address");
		String[] upAddrs =upAddr.split("\\*");
		
		System.out.println(upAddrs.length+".....................");
		if(upAddrs.length<=2){
			 province =upAddrs[0] ;
			 city = upAddrs[1];	
		}else{
			 province =upAddrs[0];
			 city =upAddrs[1];
			 area =upAddrs[2];
		}
		XhUser user = new XhUser();
		user.setId(uid);
		RecAddr recAddr = new RecAddr();
		recAddr.setXhUser(user);
		recAddr.setRecUser(recUser);
		recAddr.setRecPhone(recPhone);
		recAddr.setProvince(province);
		recAddr.setCity(city);
		recAddr.setArea(area);
		recAddr.setAddress(address);
		recAddrService.save(recAddr);
		NextLoad nextLoad = (NextLoad)session.getAttribute("nextLoad");
		if(nextLoad != null && nextLoad.getMid() != null && !nextLoad.getMid().isEmpty()){
			return "redirect:monthNextLoad";
		}
		return "redirect:nextLoad";
	}
	@RequestMapping("delAddr")
	@ResponseBody
	public Message delAddr(HttpServletRequest request,String id){
		Message message = new Message();
		RecAddr recAddr = new RecAddr();
		recAddr.setId(id);
		recAddrService.delete(recAddr);
		message.setCode("1");
		message.setMessage("删除成功");
		return message;
	}
	
	@RequestMapping("addressList")
	public String addressList(HttpServletRequest request, HttpServletResponse response, Model model,HttpSession session){
		String openId = (String)session.getAttribute("openId");
		String uid =xhUserService.findIdByOpenId(openId);
		XhUser xhUser = new XhUser();
		xhUser.setId(uid);
		RecAddr recAddr = new RecAddr();
		recAddr.setXhUser(xhUser);
		List<RecAddr> recAddres =recAddrService.findList(recAddr);
		model.addAttribute("recAddres", recAddres);
		return "modules/xhreception/addressList";
	}
	@RequestMapping("saveAddr")
	public String saveAddr(HttpServletRequest request,HttpSession session){
		String openId = (String)session.getAttribute("openId");
		String uid =xhUserService.findIdByOpenId(openId);
		String province = "";
		String city = "";
		String area = "";
		String recUser = request.getParameter("recUser");
		String recPhone = request.getParameter("recPhone");
		String upAddr = request.getParameter("upAddr");
		String address = request.getParameter("address");
		String[] upAddrs =upAddr.split("\\*");
		
		System.out.println(upAddrs.length+".....................");
		if(upAddrs.length<=2){
			 province =upAddrs[0] ;
			 city = upAddrs[1];	
		}else{
			 province =upAddrs[0];
			 city =upAddrs[1];
			 area =upAddrs[2];
		}
		XhUser user = new XhUser();
		user.setId(uid);
		RecAddr recAddr = new RecAddr();
		recAddr.setXhUser(user);
		recAddr.setRecUser(recUser);
		recAddr.setRecPhone(recPhone);
		recAddr.setProvince(province);
		recAddr.setCity(city);
		recAddr.setArea(area);
		recAddr.setAddress(address);
		recAddrService.save(recAddr);
		return "redirect:addressList";
	}
	
}
