/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.monthtime.web;

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

import com.google.common.collect.Lists;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.monthtime.entity.MonthTime;
import com.jeeplus.modules.monthtime.service.MonthTimeService;
import com.jeeplus.modules.xhmonthbuy.entity.XhMonthbuy;
import com.jeeplus.modules.xhmonthbuy.service.XhMonthbuyService;

/**
 * 包月时间设定Controller
 * @author wujianbing
 * @version 2019-04-17
 */
@Controller
@RequestMapping(value = "${adminPath}/monthtime/monthTime")
public class MonthTimeController extends BaseController {

	@Autowired
	private MonthTimeService monthTimeService;
	@Autowired
	private XhMonthbuyService xhMonthbuyService;
	@ModelAttribute
	public MonthTime get(@RequestParam(required=false) String id) {
		MonthTime entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = monthTimeService.get(id);
		}
		if (entity == null){
			entity = new MonthTime();
		}
		return entity;
	}
	
	/**
	 * 包月时间设定列表页面
	 */
	@RequiresPermissions("monthtime:monthTime:list")
	@RequestMapping(value = {"list", ""})
	public String list(MonthTime monthTime, HttpServletRequest request, HttpServletResponse response, Model model) {
		XhMonthbuy xhMonthbuy = new XhMonthbuy();
		List<XhMonthbuy> mlist = xhMonthbuyService.findList(xhMonthbuy);
		Page<MonthTime> page = monthTimeService.findPage(new Page<MonthTime>(request, response), monthTime); 
		model.addAttribute("mlist", mlist);
		model.addAttribute("page", page);
		return "modules/monthtime/monthTimeList";
	}

	/**
	 * 查看，增加，编辑包月时间设定表单页面
	 */
	@RequiresPermissions(value={"monthtime:monthTime:view","monthtime:monthTime:add","monthtime:monthTime:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(MonthTime monthTime, Model model) {
		XhMonthbuy xhMonthbuy = new XhMonthbuy();
		List<XhMonthbuy> mlist = xhMonthbuyService.findList(xhMonthbuy);
		model.addAttribute("mlist", mlist);
		model.addAttribute("monthTime", monthTime);
		return "modules/monthtime/monthTimeForm";
	}

	/**
	 * 保存包月时间设定
	 */
	@RequiresPermissions(value={"monthtime:monthTime:add","monthtime:monthTime:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(MonthTime monthTime, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, monthTime)){
			return form(monthTime, model);
		}
		if(!monthTime.getIsNewRecord()){//编辑表单保存
			MonthTime t = monthTimeService.get(monthTime.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(monthTime, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			monthTimeService.save(t);//保存
		}else{//新增表单保存
			monthTimeService.save(monthTime);//保存
		}
		addMessage(redirectAttributes, "保存包月时间设定成功");
		return "redirect:"+Global.getAdminPath()+"/monthtime/monthTime/?repage";
	}
	
	/**
	 * 删除包月时间设定
	 */
	@RequiresPermissions("monthtime:monthTime:del")
	@RequestMapping(value = "delete")
	public String delete(MonthTime monthTime, RedirectAttributes redirectAttributes) {
		monthTimeService.delete(monthTime);
		addMessage(redirectAttributes, "删除包月时间设定成功");
		return "redirect:"+Global.getAdminPath()+"/monthtime/monthTime/?repage";
	}
	
	/**
	 * 批量删除包月时间设定
	 */
	@RequiresPermissions("monthtime:monthTime:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			monthTimeService.delete(monthTimeService.get(id));
		}
		addMessage(redirectAttributes, "删除包月时间设定成功");
		return "redirect:"+Global.getAdminPath()+"/monthtime/monthTime/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("monthtime:monthTime:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(MonthTime monthTime, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "包月时间设定"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<MonthTime> page = monthTimeService.findPage(new Page<MonthTime>(request, response, -1), monthTime);
    		new ExportExcel("包月时间设定", MonthTime.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出包月时间设定记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/monthtime/monthTime/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("monthtime:monthTime:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<MonthTime> list = ei.getDataList(MonthTime.class);
			for (MonthTime monthTime : list){
				try{
					monthTimeService.save(monthTime);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条包月时间设定记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条包月时间设定记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入包月时间设定失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/monthtime/monthTime/?repage";
    }
	
	/**
	 * 下载导入包月时间设定数据模板
	 */
	@RequiresPermissions("monthtime:monthTime:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "包月时间设定数据导入模板.xlsx";
    		List<MonthTime> list = Lists.newArrayList(); 
    		new ExportExcel("包月时间设定数据", MonthTime.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/monthtime/monthTime/?repage";
    }
	
	
	

}