/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.pointinfo.web;

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
import com.jeeplus.modules.pointinfo.entity.PointInfo;
import com.jeeplus.modules.pointinfo.service.PointInfoService;

/**
 * 积分信息Controller
 * @author wujianbing
 * @version 2019-05-14
 */
@Controller
@RequestMapping(value = "${adminPath}/pointinfo/pointInfo")
public class PointInfoController extends BaseController {

	@Autowired
	private PointInfoService pointInfoService;
	
	@ModelAttribute
	public PointInfo get(@RequestParam(required=false) String id) {
		PointInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pointInfoService.get(id);
		}
		if (entity == null){
			entity = new PointInfo();
		}
		return entity;
	}
	
	/**
	 * 积分信息列表页面
	 */
	@RequiresPermissions("pointinfo:pointInfo:list")
	@RequestMapping(value = {"list", ""})
	public String list(PointInfo pointInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PointInfo> page = pointInfoService.findPage(new Page<PointInfo>(request, response), pointInfo); 
		model.addAttribute("page", page);
		return "modules/pointinfo/pointInfoList";
	}

	/**
	 * 查看，增加，编辑积分信息表单页面
	 */
	@RequiresPermissions(value={"pointinfo:pointInfo:view","pointinfo:pointInfo:add","pointinfo:pointInfo:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(PointInfo pointInfo, Model model) {
		model.addAttribute("pointInfo", pointInfo);
		return "modules/pointinfo/pointInfoForm";
	}

	/**
	 * 保存积分信息
	 */
	@RequiresPermissions(value={"pointinfo:pointInfo:add","pointinfo:pointInfo:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(PointInfo pointInfo, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, pointInfo)){
			return form(pointInfo, model);
		}
		if(!pointInfo.getIsNewRecord()){//编辑表单保存
			PointInfo t = pointInfoService.get(pointInfo.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(pointInfo, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			pointInfoService.save(t);//保存
		}else{//新增表单保存
			pointInfoService.save(pointInfo);//保存
		}
		addMessage(redirectAttributes, "保存积分信息成功");
		return "redirect:"+Global.getAdminPath()+"/pointinfo/pointInfo/?repage";
	}
	
	/**
	 * 删除积分信息
	 */
	@RequiresPermissions("pointinfo:pointInfo:del")
	@RequestMapping(value = "delete")
	public String delete(PointInfo pointInfo, RedirectAttributes redirectAttributes) {
		pointInfoService.delete(pointInfo);
		addMessage(redirectAttributes, "删除积分信息成功");
		return "redirect:"+Global.getAdminPath()+"/pointinfo/pointInfo/?repage";
	}
	
	/**
	 * 批量删除积分信息
	 */
	@RequiresPermissions("pointinfo:pointInfo:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			pointInfoService.delete(pointInfoService.get(id));
		}
		addMessage(redirectAttributes, "删除积分信息成功");
		return "redirect:"+Global.getAdminPath()+"/pointinfo/pointInfo/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("pointinfo:pointInfo:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PointInfo pointInfo, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "积分信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PointInfo> page = pointInfoService.findPage(new Page<PointInfo>(request, response, -1), pointInfo);
    		new ExportExcel("积分信息", PointInfo.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出积分信息记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/pointinfo/pointInfo/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("pointinfo:pointInfo:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<PointInfo> list = ei.getDataList(PointInfo.class);
			for (PointInfo pointInfo : list){
				try{
					pointInfoService.save(pointInfo);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条积分信息记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条积分信息记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入积分信息失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/pointinfo/pointInfo/?repage";
    }
	
	/**
	 * 下载导入积分信息数据模板
	 */
	@RequiresPermissions("pointinfo:pointInfo:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "积分信息数据导入模板.xlsx";
    		List<PointInfo> list = Lists.newArrayList(); 
    		new ExportExcel("积分信息数据", PointInfo.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/pointinfo/pointInfo/?repage";
    }
	
	
	

}