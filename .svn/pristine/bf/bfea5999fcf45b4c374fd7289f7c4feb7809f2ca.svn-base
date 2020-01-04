/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhuser.web;

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
import com.jeeplus.modules.xhuser.entity.XhUser;
import com.jeeplus.modules.xhuser.service.XhUserService;

/**
 * 用户信息Controller
 * @author wujianbing
 * @version 2019-04-09
 */
@Controller
@RequestMapping(value = "${adminPath}/xhuser/xhUser")
public class XhUserController extends BaseController {

	@Autowired
	private XhUserService xhUserService;
	
	@ModelAttribute
	public XhUser get(@RequestParam(required=false) String id) {
		XhUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xhUserService.get(id);
		}
		if (entity == null){
			entity = new XhUser();
		}
		return entity;
	}
	
	/**
	 * 用户信息列表页面
	 */
	@RequiresPermissions("xhuser:xhUser:list")
	@RequestMapping(value = {"list", ""})
	public String list(XhUser xhUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XhUser> page = xhUserService.findPage(new Page<XhUser>(request, response), xhUser); 
		model.addAttribute("page", page);
		return "modules/xhuser/xhUserList";
	}

	/**
	 * 查看，增加，编辑用户信息表单页面
	 */
	@RequiresPermissions(value={"xhuser:xhUser:view","xhuser:xhUser:add","xhuser:xhUser:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(XhUser xhUser, Model model) {
		model.addAttribute("xhUser", xhUser);
		return "modules/xhuser/xhUserForm";
	}

	/**
	 * 保存用户信息
	 */
	@RequiresPermissions(value={"xhuser:xhUser:add","xhuser:xhUser:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(XhUser xhUser, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, xhUser)){
			return form(xhUser, model);
		}
		if(!xhUser.getIsNewRecord()){//编辑表单保存
			XhUser t = xhUserService.get(xhUser.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(xhUser, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			xhUserService.save(t);//保存
		}else{//新增表单保存
			xhUserService.save(xhUser);//保存
		}
		addMessage(redirectAttributes, "保存用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/xhuser/xhUser/?repage";
	}
	
	/**
	 * 删除用户信息
	 */
	@RequiresPermissions("xhuser:xhUser:del")
	@RequestMapping(value = "delete")
	public String delete(XhUser xhUser, RedirectAttributes redirectAttributes) {
		xhUserService.delete(xhUser);
		addMessage(redirectAttributes, "删除用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/xhuser/xhUser/?repage";
	}
	
	/**
	 * 批量删除用户信息
	 */
	@RequiresPermissions("xhuser:xhUser:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			xhUserService.delete(xhUserService.get(id));
		}
		addMessage(redirectAttributes, "删除用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/xhuser/xhUser/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("xhuser:xhUser:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(XhUser xhUser, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<XhUser> page = xhUserService.findPage(new Page<XhUser>(request, response, -1), xhUser);
    		new ExportExcel("用户信息", XhUser.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户信息记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhuser/xhUser/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("xhuser:xhUser:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<XhUser> list = ei.getDataList(XhUser.class);
			for (XhUser xhUser : list){
				try{
					xhUserService.save(xhUser);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户信息记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条用户信息记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhuser/xhUser/?repage";
    }
	
	/**
	 * 下载导入用户信息数据模板
	 */
	@RequiresPermissions("xhuser:xhUser:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "用户信息数据导入模板.xlsx";
    		List<XhUser> list = Lists.newArrayList(); 
    		new ExportExcel("用户信息数据", XhUser.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhuser/xhUser/?repage";
    }
	
	
	

}