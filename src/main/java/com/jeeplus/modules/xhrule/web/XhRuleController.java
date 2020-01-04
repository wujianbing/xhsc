/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhrule.web;

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
import com.jeeplus.modules.xhgoods.entity.XhGoods;
import com.jeeplus.modules.xhgoods.service.XhGoodsService;
import com.jeeplus.modules.xhrule.entity.XhRule;
import com.jeeplus.modules.xhrule.service.XhRuleService;

/**
 * 商品规格Controller
 * @author wujianbing
 * @version 2019-04-15
 */
@Controller
@RequestMapping(value = "${adminPath}/xhrule/xhRule")
public class XhRuleController extends BaseController {

	@Autowired
	private XhRuleService xhRuleService;
	@Autowired
	private XhGoodsService xhGoodsService;
	@ModelAttribute
	public XhRule get(@RequestParam(required=false) String id) {
		XhRule entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xhRuleService.get(id);
		}
		if (entity == null){
			entity = new XhRule();
		}
		return entity;
	}
	
	/**
	 * 商品规格列表页面
	 */
	@RequiresPermissions("xhrule:xhRule:list")
	@RequestMapping(value = {"list", ""})
	public String list(XhRule xhRule, HttpServletRequest request, HttpServletResponse response, Model model) {
		XhGoods xhGoods1 = new XhGoods();
		List<XhGoods> xlist=xhGoodsService.findList(xhGoods1);
		model.addAttribute("xlist",xlist);
		Page<XhRule> page = xhRuleService.findPage(new Page<XhRule>(request, response), xhRule); 
		model.addAttribute("page", page);
		return "modules/xhrule/xhRuleList";
	}

	/**
	 * 查看，增加，编辑商品规格表单页面
	 */
	@RequiresPermissions(value={"xhrule:xhRule:view","xhrule:xhRule:add","xhrule:xhRule:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(XhRule xhRule, Model model) {
		XhGoods xhGoods = new XhGoods();
		List<XhGoods> xlist=xhGoodsService.findList(xhGoods);
		model.addAttribute("xlist",xlist);
		model.addAttribute("xhRule", xhRule);
		return "modules/xhrule/xhRuleForm";
	}

	/**
	 * 保存商品规格
	 */
	@RequiresPermissions(value={"xhrule:xhRule:add","xhrule:xhRule:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(XhRule xhRule, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, xhRule)){
			return form(xhRule, model);
		}
		if(!xhRule.getIsNewRecord()){//编辑表单保存
			XhRule t = xhRuleService.get(xhRule.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(xhRule, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			xhRuleService.save(t);//保存
		}else{//新增表单保存
			xhRuleService.save(xhRule);//保存
		}
		addMessage(redirectAttributes, "保存商品规格成功");
		return "redirect:"+Global.getAdminPath()+"/xhrule/xhRule/?repage";
	}
	
	/**
	 * 删除商品规格
	 */
	@RequiresPermissions("xhrule:xhRule:del")
	@RequestMapping(value = "delete")
	public String delete(XhRule xhRule, RedirectAttributes redirectAttributes) {
		xhRuleService.delete(xhRule);
		addMessage(redirectAttributes, "删除商品规格成功");
		return "redirect:"+Global.getAdminPath()+"/xhrule/xhRule/?repage";
	}
	
	/**
	 * 批量删除商品规格
	 */
	@RequiresPermissions("xhrule:xhRule:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			xhRuleService.delete(xhRuleService.get(id));
		}
		addMessage(redirectAttributes, "删除商品规格成功");
		return "redirect:"+Global.getAdminPath()+"/xhrule/xhRule/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("xhrule:xhRule:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(XhRule xhRule, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "商品规格"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<XhRule> page = xhRuleService.findPage(new Page<XhRule>(request, response, -1), xhRule);
    		new ExportExcel("商品规格", XhRule.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出商品规格记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhrule/xhRule/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("xhrule:xhRule:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<XhRule> list = ei.getDataList(XhRule.class);
			for (XhRule xhRule : list){
				try{
					xhRuleService.save(xhRule);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条商品规格记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条商品规格记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入商品规格失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhrule/xhRule/?repage";
    }
	
	/**
	 * 下载导入商品规格数据模板
	 */
	@RequiresPermissions("xhrule:xhRule:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "商品规格数据导入模板.xlsx";
    		List<XhRule> list = Lists.newArrayList(); 
    		new ExportExcel("商品规格数据", XhRule.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhrule/xhRule/?repage";
    }
	
	
	

}