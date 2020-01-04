/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhshoper.web;

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
import com.jeeplus.modules.xhshoper.entity.XhShopcar;
import com.jeeplus.modules.xhshoper.service.XhShopcarService;

/**
 * 购物车Controller
 * @author wujianbing
 * @version 2019-04-28
 */
@Controller
@RequestMapping(value = "${adminPath}/xhshoper/xhShopcar")
public class XhShopcarController extends BaseController {

	@Autowired
	private XhShopcarService xhShopcarService;
	
	@ModelAttribute
	public XhShopcar get(@RequestParam(required=false) String id) {
		XhShopcar entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xhShopcarService.get(id);
		}
		if (entity == null){
			entity = new XhShopcar();
		}
		return entity;
	}
	
	/**
	 * 购物车列表页面
	 */
	@RequiresPermissions("xhshoper:xhShopcar:list")
	@RequestMapping(value = {"list", ""})
	public String list(XhShopcar xhShopcar, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XhShopcar> page = xhShopcarService.findPage(new Page<XhShopcar>(request, response), xhShopcar); 
		model.addAttribute("page", page);
		return "modules/xhshoper/xhShopcarList";
	}

	/**
	 * 查看，增加，编辑购物车表单页面
	 */
	@RequiresPermissions(value={"xhshoper:xhShopcar:view","xhshoper:xhShopcar:add","xhshoper:xhShopcar:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(XhShopcar xhShopcar, Model model) {
		model.addAttribute("xhShopcar", xhShopcar);
		return "modules/xhshoper/xhShopcarForm";
	}

	/**
	 * 保存购物车
	 */
	@RequiresPermissions(value={"xhshoper:xhShopcar:add","xhshoper:xhShopcar:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(XhShopcar xhShopcar, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, xhShopcar)){
			return form(xhShopcar, model);
		}
		if(!xhShopcar.getIsNewRecord()){//编辑表单保存
			XhShopcar t = xhShopcarService.get(xhShopcar.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(xhShopcar, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			xhShopcarService.save(t);//保存
		}else{//新增表单保存
			xhShopcarService.save(xhShopcar);//保存
		}
		addMessage(redirectAttributes, "保存购物车成功");
		return "redirect:"+Global.getAdminPath()+"/xhshoper/xhShopcar/?repage";
	}
	
	/**
	 * 删除购物车
	 */
	@RequiresPermissions("xhshoper:xhShopcar:del")
	@RequestMapping(value = "delete")
	public String delete(XhShopcar xhShopcar, RedirectAttributes redirectAttributes) {
		xhShopcarService.delete(xhShopcar);
		addMessage(redirectAttributes, "删除购物车成功");
		return "redirect:"+Global.getAdminPath()+"/xhshoper/xhShopcar/?repage";
	}
	
	/**
	 * 批量删除购物车
	 */
	@RequiresPermissions("xhshoper:xhShopcar:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			xhShopcarService.delete(xhShopcarService.get(id));
		}
		addMessage(redirectAttributes, "删除购物车成功");
		return "redirect:"+Global.getAdminPath()+"/xhshoper/xhShopcar/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("xhshoper:xhShopcar:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(XhShopcar xhShopcar, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "购物车"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<XhShopcar> page = xhShopcarService.findPage(new Page<XhShopcar>(request, response, -1), xhShopcar);
    		new ExportExcel("购物车", XhShopcar.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出购物车记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhshoper/xhShopcar/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("xhshoper:xhShopcar:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<XhShopcar> list = ei.getDataList(XhShopcar.class);
			for (XhShopcar xhShopcar : list){
				try{
					xhShopcarService.save(xhShopcar);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条购物车记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条购物车记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入购物车失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhshoper/xhShopcar/?repage";
    }
	
	/**
	 * 下载导入购物车数据模板
	 */
	@RequiresPermissions("xhshoper:xhShopcar:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "购物车数据导入模板.xlsx";
    		List<XhShopcar> list = Lists.newArrayList(); 
    		new ExportExcel("购物车数据", XhShopcar.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhshoper/xhShopcar/?repage";
    }
	
	
	

}