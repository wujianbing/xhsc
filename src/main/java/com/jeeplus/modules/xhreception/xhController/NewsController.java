package com.jeeplus.modules.xhreception.xhController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewsController {
	
	@RequestMapping("news")
	public String xhNewsList(){
		return "modules/xhreception/xhNews";
	}
}
