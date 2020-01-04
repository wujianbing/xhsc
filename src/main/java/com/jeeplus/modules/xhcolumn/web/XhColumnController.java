/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhcolumn.web;

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
import com.jeeplus.modules.xhcolumn.entity.XhColumn;
import com.jeeplus.modules.xhcolumn.service.XhColumnService;

/**
 * 鲜花栏目Controller
 * @author wujianbing
 * @version 2019-04-08
 */
@Controller
@RequestMapping(value = "${adminPath}/xhcolumn/xhColumn")
public class XhColumnController extends BaseController {

	@Autowired
	private XhColumnService xhColumnService;
	
	@ModelAttribute
	public XhColumn get(@RequestParam(required=false) String id) {
		XhColumn entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xhColumnService.get(id);
		}
		if (entity == null){
			entity = new XhColumn();
		}
		return entity;
	}
	
	/**
	 * 鲜花栏目列表页面
	 */
	@RequiresPermissions("xhcolumn:xhColumn:list")
	@RequestMapping(value = {"list", ""})
	public String list(XhColumn xhColumn, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<XhColumn> list = xhColumnService.findList(xhColumn); 
		model.addAttribute("list", list);
		return "modules/xhcolumn/xhColumnList";
	}

	/**
	 * 查看，增加，编辑鲜花栏目表单页面
	 */
	@RequiresPermissions(value={"xhcolumn:xhColumn:view","xhcolumn:xhColumn:add","xhcolumn:xhColumn:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(XhColumn xhColumn, Model model) {
		if (xhColumn.getParent()!=null && StringUtils.isNotBlank(xhColumn.getParent().getId())){
			xhColumn.setParent(xhColumnService.get(xhColumn.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(xhColumn.getId())){
				XhColumn xhColumnChild = new XhColumn();
				xhColumnChild.setParent(new XhColumn(xhColumn.getParent().getId()));
				List<XhColumn> list = xhColumnService.findList(xhColumn); 
				if (list.size() > 0){
					xhColumn.setSort(list.get(list.size()-1).getSort());
					if (xhColumn.getSort() != null){
						xhColumn.setSort(xhColumn.getSort() + 30);
					}
				}
			}
		}
		if (xhColumn.getSort() == null){
			xhColumn.setSort(30);
		}
		model.addAttribute("xhColumn", xhColumn);
		return "modules/xhcolumn/xhColumnForm";
	}

	/**
	 * 保存鲜花栏目
	 */
	@RequiresPermissions(value={"xhcolumn:xhColumn:add","xhcolumn:xhColumn:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(XhColumn xhColumn, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, xhColumn)){
			return form(xhColumn, model);
		}
		if(!xhColumn.getIsNewRecord()){//编辑表单保存
			XhColumn t = xhColumnService.get(xhColumn.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(xhColumn, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			xhColumnService.save(t);//保存
		}else{//新增表单保存
			xhColumnService.save(xhColumn);//保存
		}
		addMessage(redirectAttributes, "保存鲜花栏目成功");
		return "redirect:"+Global.getAdminPath()+"/xhcolumn/xhColumn/?repage";
	}
	
	/**
	 * 删除鲜花栏目
	 */
	@RequiresPermissions("xhcolumn:xhColumn:del")
	@RequestMapping(value = "delete")
	public String delete(XhColumn xhColumn, RedirectAttributes redirectAttributes) {
		xhColumnService.delete(xhColumn);
		addMessage(redirectAttributes, "删除鲜花栏目成功");
		return "redirect:"+Global.getAdminPath()+"/xhcolumn/xhColumn/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<XhColumn> list = xhColumnService.findList(new XhColumn());
		for (int i=0; i<list.size(); i++){
			XhColumn e = list.get(i);
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