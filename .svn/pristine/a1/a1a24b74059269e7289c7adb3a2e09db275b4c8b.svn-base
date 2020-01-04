/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhwxmsg.web;

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
import com.jeeplus.modules.xhwxmsg.entity.XhWechatmsg;
import com.jeeplus.modules.xhwxmsg.service.XhWechatmsgService;

/**
 * 微信支付配置信息Controller
 * @author lk
 * @version 2019-05-06
 */
@Controller
@RequestMapping(value = "${adminPath}/xhwxmsg/xhWechatmsg")
public class XhWechatmsgController extends BaseController {

	@Autowired
	private XhWechatmsgService xhWechatmsgService;
	
	@ModelAttribute
	public XhWechatmsg get(@RequestParam(required=false) String id) {
		XhWechatmsg entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xhWechatmsgService.get(id);
		}
		if (entity == null){
			entity = new XhWechatmsg();
		}
		return entity;
	}
	
	/**
	 * 微信支付配置信息列表页面
	 */
	@RequiresPermissions("xhwxmsg:xhWechatmsg:list")
	@RequestMapping(value = {"list", ""})
	public String list(XhWechatmsg xhWechatmsg, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XhWechatmsg> page = xhWechatmsgService.findPage(new Page<XhWechatmsg>(request, response), xhWechatmsg); 
		model.addAttribute("page", page);
		return "modules/xhwxmsg/xhWechatmsgList";
	}

	/**
	 * 查看，增加，编辑微信支付配置信息表单页面
	 */
	@RequiresPermissions(value={"xhwxmsg:xhWechatmsg:view","xhwxmsg:xhWechatmsg:add","xhwxmsg:xhWechatmsg:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(XhWechatmsg xhWechatmsg, Model model) {
		model.addAttribute("xhWechatmsg", xhWechatmsg);
		return "modules/xhwxmsg/xhWechatmsgForm";
	}

	/**
	 * 保存微信支付配置信息
	 */
	@RequiresPermissions(value={"xhwxmsg:xhWechatmsg:add","xhwxmsg:xhWechatmsg:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(XhWechatmsg xhWechatmsg, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, xhWechatmsg)){
			return form(xhWechatmsg, model);
		}
		if(!xhWechatmsg.getIsNewRecord()){//编辑表单保存
			XhWechatmsg t = xhWechatmsgService.get(xhWechatmsg.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(xhWechatmsg, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			xhWechatmsgService.save(t);//保存
		}else{//新增表单保存
			xhWechatmsgService.save(xhWechatmsg);//保存
		}
		addMessage(redirectAttributes, "保存微信支付配置信息成功");
		return "redirect:"+Global.getAdminPath()+"/xhwxmsg/xhWechatmsg/?repage";
	}
	
	/**
	 * 删除微信支付配置信息
	 */
	@RequiresPermissions("xhwxmsg:xhWechatmsg:del")
	@RequestMapping(value = "delete")
	public String delete(XhWechatmsg xhWechatmsg, RedirectAttributes redirectAttributes) {
		xhWechatmsgService.delete(xhWechatmsg);
		addMessage(redirectAttributes, "删除微信支付配置信息成功");
		return "redirect:"+Global.getAdminPath()+"/xhwxmsg/xhWechatmsg/?repage";
	}
	
	/**
	 * 批量删除微信支付配置信息
	 */
	@RequiresPermissions("xhwxmsg:xhWechatmsg:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			xhWechatmsgService.delete(xhWechatmsgService.get(id));
		}
		addMessage(redirectAttributes, "删除微信支付配置信息成功");
		return "redirect:"+Global.getAdminPath()+"/xhwxmsg/xhWechatmsg/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("xhwxmsg:xhWechatmsg:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(XhWechatmsg xhWechatmsg, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "微信支付配置信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<XhWechatmsg> page = xhWechatmsgService.findPage(new Page<XhWechatmsg>(request, response, -1), xhWechatmsg);
    		new ExportExcel("微信支付配置信息", XhWechatmsg.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出微信支付配置信息记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhwxmsg/xhWechatmsg/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("xhwxmsg:xhWechatmsg:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<XhWechatmsg> list = ei.getDataList(XhWechatmsg.class);
			for (XhWechatmsg xhWechatmsg : list){
				try{
					xhWechatmsgService.save(xhWechatmsg);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条微信支付配置信息记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条微信支付配置信息记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入微信支付配置信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhwxmsg/xhWechatmsg/?repage";
    }
	
	/**
	 * 下载导入微信支付配置信息数据模板
	 */
	@RequiresPermissions("xhwxmsg:xhWechatmsg:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "微信支付配置信息数据导入模板.xlsx";
    		List<XhWechatmsg> list = Lists.newArrayList(); 
    		new ExportExcel("微信支付配置信息数据", XhWechatmsg.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhwxmsg/xhWechatmsg/?repage";
    }
	
	
	

}