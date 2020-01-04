/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhnews.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jeeplus.modules.xhcolumn.entity.XhColumn;
import com.google.common.collect.Lists;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.modules.xhnews.entity.XhNews;
import com.jeeplus.modules.xhnews.service.XhNewsService;

/**
 * 鲜花资讯Controller
 * @author wujianbing
 * @version 2019-04-09
 */
@Controller
@RequestMapping(value = "${adminPath}/xhnews/xhNews")
public class XhNewsController extends BaseController {

	@Autowired
	private XhNewsService xhNewsService;
	
	@ModelAttribute
	public XhNews get(@RequestParam(required=false) String id) {
		XhNews entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xhNewsService.get(id);
		}
		if (entity == null){
			entity = new XhNews();
		}
		return entity;
	}
	
	/**
	 * 鲜花资讯列表页面
	 */
	@RequiresPermissions("xhnews:xhNews:list")
	@RequestMapping(value = {"list", ""})
	public String list(XhNews xhNews, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XhNews> page = xhNewsService.findPage(new Page<XhNews>(request, response), xhNews); 
		model.addAttribute("page", page);
		return "modules/xhnews/xhNewsList";
	}

	/**
	 * 查看，增加，编辑鲜花资讯表单页面
	 */
	@RequiresPermissions(value={"xhnews:xhNews:view","xhnews:xhNews:add","xhnews:xhNews:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(XhNews xhNews, Model model) {
		model.addAttribute("xhNews", xhNews);
		return "modules/xhnews/xhNewsForm";
	}

	/**
	 * 保存鲜花资讯
	 */
	@RequiresPermissions(value={"xhnews:xhNews:add","xhnews:xhNews:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(XhNews xhNews, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, xhNews)){
			return form(xhNews, model);
		}
		if(!xhNews.getIsNewRecord()){//编辑表单保存
			XhNews t = xhNewsService.get(xhNews.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(xhNews, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			xhNewsService.save(t);//保存
		}else{//新增表单保存
			xhNewsService.save(xhNews);//保存
		}
		addMessage(redirectAttributes, "保存鲜花资讯成功");
		return "redirect:"+Global.getAdminPath()+"/xhnews/xhNews/?repage";
	}
	
	/**
	 * 删除鲜花资讯
	 */
	@RequiresPermissions("xhnews:xhNews:del")
	@RequestMapping(value = "delete")
	public String delete(XhNews xhNews, RedirectAttributes redirectAttributes) {
		xhNewsService.delete(xhNews);
		addMessage(redirectAttributes, "删除鲜花资讯成功");
		return "redirect:"+Global.getAdminPath()+"/xhnews/xhNews/?repage";
	}
	
	/**
	 * 批量删除鲜花资讯
	 */
	@RequiresPermissions("xhnews:xhNews:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			xhNewsService.delete(xhNewsService.get(id));
		}
		addMessage(redirectAttributes, "删除鲜花资讯成功");
		return "redirect:"+Global.getAdminPath()+"/xhnews/xhNews/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("xhnews:xhNews:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(XhNews xhNews, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "鲜花资讯"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<XhNews> page = xhNewsService.findPage(new Page<XhNews>(request, response, -1), xhNews);
    		new ExportExcel("鲜花资讯", XhNews.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出鲜花资讯记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhnews/xhNews/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("xhnews:xhNews:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<XhNews> list = ei.getDataList(XhNews.class);
			for (XhNews xhNews : list){
				try{
					xhNewsService.save(xhNews);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条鲜花资讯记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条鲜花资讯记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入鲜花资讯失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhnews/xhNews/?repage";
    }
	
	/**
	 * 下载导入鲜花资讯数据模板
	 */
	@RequiresPermissions("xhnews:xhNews:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "鲜花资讯数据导入模板.xlsx";
    		List<XhNews> list = Lists.newArrayList(); 
    		new ExportExcel("鲜花资讯数据", XhNews.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhnews/xhNews/?repage";
    }
	
	
	/**
	 * 选择栏目外键
	 */
	@RequestMapping(value = "selectxhColumn")
	public String selectxhColumn(XhColumn xhColumn, String url, String fieldLabels, String fieldKeys, String searchLabel, String searchKey, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XhColumn> page = xhNewsService.findPageByxhColumn(new Page<XhColumn>(request, response),  xhColumn);
		try {
			fieldLabels = URLDecoder.decode(fieldLabels, "UTF-8");
			fieldKeys = URLDecoder.decode(fieldKeys, "UTF-8");
			searchLabel = URLDecoder.decode(searchLabel, "UTF-8");
			searchKey = URLDecoder.decode(searchKey, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		model.addAttribute("labelNames", fieldLabels.split("\\|"));
		model.addAttribute("labelValues", fieldKeys.split("\\|"));
		model.addAttribute("fieldLabels", fieldLabels);
		model.addAttribute("fieldKeys", fieldKeys);
		model.addAttribute("url", url);
		model.addAttribute("searchLabel", searchLabel);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("obj", xhColumn);
		model.addAttribute("page", page);
		return "modules/sys/gridselect";
	}
	

}