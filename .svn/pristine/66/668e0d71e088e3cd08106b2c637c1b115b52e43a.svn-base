/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.recaddress.web;

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
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.modules.recaddress.entity.RecAddr;
import com.jeeplus.modules.recaddress.service.RecAddrService;

/**
 * 收货地址Controller
 * @author wujianbing
 * @version 2019-04-09
 */
@Controller
@RequestMapping(value = "${adminPath}/recaddress/recAddr")
public class RecAddrController extends BaseController {

	@Autowired
	private RecAddrService recAddrService;
	
	@ModelAttribute
	public RecAddr get(@RequestParam(required=false) String id) {
		RecAddr entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = recAddrService.get(id);
		}
		if (entity == null){
			entity = new RecAddr();
		}
		return entity;
	}
	
	/**
	 * 收货地址列表页面
	 */
	@RequiresPermissions("recaddress:recAddr:list")
	@RequestMapping(value = {"list", ""})
	public String list(RecAddr recAddr, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RecAddr> page = recAddrService.findPage(new Page<RecAddr>(request, response), recAddr); 
		model.addAttribute("page", page);
		return "modules/recaddress/recAddrList";
	}

	/**
	 * 查看，增加，编辑收货地址表单页面
	 */
	@RequiresPermissions(value={"recaddress:recAddr:view","recaddress:recAddr:add","recaddress:recAddr:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(RecAddr recAddr, Model model) {
		model.addAttribute("recAddr", recAddr);
		return "modules/recaddress/recAddrForm";
	}

	/**
	 * 保存收货地址
	 */
	@RequiresPermissions(value={"recaddress:recAddr:add","recaddress:recAddr:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(RecAddr recAddr, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, recAddr)){
			return form(recAddr, model);
		}
		if(!recAddr.getIsNewRecord()){//编辑表单保存
			RecAddr t = recAddrService.get(recAddr.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(recAddr, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			recAddrService.save(t);//保存
		}else{//新增表单保存
			recAddrService.save(recAddr);//保存
		}
		addMessage(redirectAttributes, "保存收货地址成功");
		return "redirect:"+Global.getAdminPath()+"/recaddress/recAddr/?repage";
	}
	
	/**
	 * 删除收货地址
	 */
	@RequiresPermissions("recaddress:recAddr:del")
	@RequestMapping(value = "delete")
	public String delete(RecAddr recAddr, RedirectAttributes redirectAttributes) {
		recAddrService.delete(recAddr);
		addMessage(redirectAttributes, "删除收货地址成功");
		return "redirect:"+Global.getAdminPath()+"/recaddress/recAddr/?repage";
	}
	
	/**
	 * 批量删除收货地址
	 */
	@RequiresPermissions("recaddress:recAddr:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			recAddrService.delete(recAddrService.get(id));
		}
		addMessage(redirectAttributes, "删除收货地址成功");
		return "redirect:"+Global.getAdminPath()+"/recaddress/recAddr/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("recaddress:recAddr:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(RecAddr recAddr, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "收货地址"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<RecAddr> page = recAddrService.findPage(new Page<RecAddr>(request, response, -1), recAddr);
    		new ExportExcel("收货地址", RecAddr.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出收货地址记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/recaddress/recAddr/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("recaddress:recAddr:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<RecAddr> list = ei.getDataList(RecAddr.class);
			for (RecAddr recAddr : list){
				try{
					recAddrService.save(recAddr);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条收货地址记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条收货地址记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入收货地址失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/recaddress/recAddr/?repage";
    }
	
	/**
	 * 下载导入收货地址数据模板
	 */
	@RequiresPermissions("recaddress:recAddr:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "收货地址数据导入模板.xlsx";
    		List<RecAddr> list = Lists.newArrayList(); 
    		new ExportExcel("收货地址数据", RecAddr.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/recaddress/recAddr/?repage";
    }
	
	
	

}