/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.pointgoods.web;

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
import com.jeeplus.common.utils.FileUtils;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.modules.pointgoods.entity.PointGoods;
import com.jeeplus.modules.pointgoods.service.PointGoodsService;
import com.jeeplus.modules.sys.entity.UploadFile;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.service.UploadFileService;
import com.jeeplus.modules.sys.utils.UserUtils;

/**
 * 积分商城Controller
 * @author wujianbing
 * @version 2019-05-14
 */
@Controller
@RequestMapping(value = "${adminPath}/pointgoods/pointGoods")
public class PointGoodsController extends BaseController {

	@Autowired
	private PointGoodsService pointGoodsService;
	@Autowired
	private UploadFileService uploadFileService;
	@ModelAttribute
	public PointGoods get(@RequestParam(required=false) String id) {
		PointGoods entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pointGoodsService.get(id);
		}
		if (entity == null){
			entity = new PointGoods();
		}
		return entity;
	}
	
	/**
	 * 积分商城列表页面
	 */
	@RequiresPermissions("pointgoods:pointGoods:list")
	@RequestMapping(value = {"list", ""})
	public String list(PointGoods pointGoods, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PointGoods> page = pointGoodsService.findPage(new Page<PointGoods>(request, response), pointGoods); 
		model.addAttribute("page", page);
		return "modules/pointgoods/pointGoodsList";
	}

	/**
	 * 查看，增加，编辑积分商城表单页面
	 */
	@RequiresPermissions(value={"pointgoods:pointGoods:view","pointgoods:pointGoods:add","pointgoods:pointGoods:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(PointGoods pointGoods, Model model) {
		model.addAttribute("pointGoods", pointGoods);
		return "modules/pointgoods/pointGoodsForm";
	}

	/**
	 * 保存积分商城
	 */
	@RequiresPermissions(value={"pointgoods:pointGoods:add","pointgoods:pointGoods:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(PointGoods pointGoods, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) throws Exception{
		if (!beanValidator(model, pointGoods)){
			return form(pointGoods, model);
		}
		MultipartFile multipartFile = FileUtils.getMultipartFile(request, "imgNews");
		User user = UserUtils.getUser();
		UploadFile uploadFile = new UploadFile();
		if (multipartFile.getSize()>0){
			uploadFile = FileUtils.saveNomalFile(multipartFile, "imgNews", user);
			uploadFileService.saveAndReturnId(uploadFile);
			pointGoods.setUploadFile(uploadFileService.get(uploadFile.getId()));
		}
		if(!pointGoods.getIsNewRecord()){//编辑表单保存
			PointGoods t = pointGoodsService.get(pointGoods.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(pointGoods, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			pointGoodsService.save(t);//保存
		}else{//新增表单保存
			pointGoodsService.save(pointGoods);//保存
		}
		addMessage(redirectAttributes, "保存积分商城成功");
		return "redirect:"+Global.getAdminPath()+"/pointgoods/pointGoods/?repage";
	}
	
	/**
	 * 删除积分商城
	 */
	@RequiresPermissions("pointgoods:pointGoods:del")
	@RequestMapping(value = "delete")
	public String delete(PointGoods pointGoods, RedirectAttributes redirectAttributes) {
		pointGoodsService.delete(pointGoods);
		addMessage(redirectAttributes, "删除积分商城成功");
		return "redirect:"+Global.getAdminPath()+"/pointgoods/pointGoods/?repage";
	}
	
	/**
	 * 批量删除积分商城
	 */
	@RequiresPermissions("pointgoods:pointGoods:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			pointGoodsService.delete(pointGoodsService.get(id));
		}
		addMessage(redirectAttributes, "删除积分商城成功");
		return "redirect:"+Global.getAdminPath()+"/pointgoods/pointGoods/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("pointgoods:pointGoods:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(PointGoods pointGoods, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "积分商城"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<PointGoods> page = pointGoodsService.findPage(new Page<PointGoods>(request, response, -1), pointGoods);
    		new ExportExcel("积分商城", PointGoods.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出积分商城记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/pointgoods/pointGoods/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("pointgoods:pointGoods:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<PointGoods> list = ei.getDataList(PointGoods.class);
			for (PointGoods pointGoods : list){
				try{
					pointGoodsService.save(pointGoods);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条积分商城记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条积分商城记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入积分商城失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/pointgoods/pointGoods/?repage";
    }
	
	/**
	 * 下载导入积分商城数据模板
	 */
	@RequiresPermissions("pointgoods:pointGoods:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "积分商城数据导入模板.xlsx";
    		List<PointGoods> list = Lists.newArrayList(); 
    		new ExportExcel("积分商城数据", PointGoods.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/pointgoods/pointGoods/?repage";
    }
	
	
	

}