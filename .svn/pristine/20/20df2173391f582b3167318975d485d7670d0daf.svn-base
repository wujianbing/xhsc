/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhcategory.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.xhcategory.entity.XhCategory;
import com.jeeplus.modules.xhcategory.service.XhCategoryService;

/**
 * 商品分类Controller
 * @author wujianbing
 * @version 2019-04-03
 */
@Controller
@RequestMapping(value = "${adminPath}/xhcategory/xhCategory")
public class XhCategoryController extends BaseController {

	@Autowired
	private XhCategoryService xhCategoryService;
	
	@ModelAttribute
	public XhCategory get(@RequestParam(required=false) String id) {
		XhCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xhCategoryService.get(id);
		}
		if (entity == null){
			entity = new XhCategory();
		}
		return entity;
	}
	
	/**
	 * 商品分类列表页面
	 */
	@RequiresPermissions("xhcategory:xhCategory:list")
	@RequestMapping(value = {"list", ""})
	public String list(XhCategory xhCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<XhCategory> list = xhCategoryService.findList(xhCategory); 
		model.addAttribute("list", list);
		return "modules/xhcategory/xhCategoryList";
	}

	/**
	 * 查看，增加，编辑商品分类表单页面
	 */
	@RequiresPermissions(value={"xhcategory:xhCategory:view","xhcategory:xhCategory:add","xhcategory:xhCategory:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(XhCategory xhCategory, Model model) {
		if (xhCategory.getParent()!=null && StringUtils.isNotBlank(xhCategory.getParent().getId())){
			xhCategory.setParent(xhCategoryService.get(xhCategory.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(xhCategory.getId())){
				XhCategory xhCategoryChild = new XhCategory();
				xhCategoryChild.setParent(new XhCategory(xhCategory.getParent().getId()));
				List<XhCategory> list = xhCategoryService.findList(xhCategory); 
				if (list.size() > 0){
					xhCategory.setSort(list.get(list.size()-1).getSort());
					if (xhCategory.getSort() != null){
						xhCategory.setSort(xhCategory.getSort() + 30);
					}
				}
			}
		}
		if (xhCategory.getSort() == null){
			xhCategory.setSort(30);
		}
		model.addAttribute("xhCategory", xhCategory);
		return "modules/xhcategory/xhCategoryForm";
	}

	/**
	 * 保存商品分类
	 */
	@RequiresPermissions(value={"xhcategory:xhCategory:add","xhcategory:xhCategory:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(XhCategory xhCategory, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, xhCategory)){
			return form(xhCategory, model);
		}
		if(!xhCategory.getIsNewRecord()){//编辑表单保存
			XhCategory t = xhCategoryService.get(xhCategory.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(xhCategory, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			xhCategoryService.save(t);//保存
		}else{//新增表单保存
			xhCategoryService.save(xhCategory);//保存
		}
		addMessage(redirectAttributes, "保存商品分类成功");
		return "redirect:"+Global.getAdminPath()+"/xhcategory/xhCategory/?repage";
	}
	
	/**
	 * 删除商品分类
	 */
	@RequiresPermissions("xhcategory:xhCategory:del")
	@RequestMapping(value = "delete")
	public String delete(XhCategory xhCategory, RedirectAttributes redirectAttributes) {
		xhCategoryService.delete(xhCategory);
		addMessage(redirectAttributes, "删除商品分类成功");
		return "redirect:"+Global.getAdminPath()+"/xhcategory/xhCategory/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<XhCategory> list = xhCategoryService.findList(new XhCategory());
		for (int i=0; i<list.size(); i++){
			XhCategory e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
}