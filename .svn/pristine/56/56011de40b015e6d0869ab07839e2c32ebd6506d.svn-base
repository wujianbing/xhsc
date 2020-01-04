/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhrim.web;

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
import com.jeeplus.modules.sys.entity.UploadFile;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.service.UploadFileService;
import com.jeeplus.modules.sys.utils.UserUtils;
import com.jeeplus.modules.xhgoods.entity.XhGoods;
import com.jeeplus.modules.xhgoods.service.XhGoodsService;
import com.jeeplus.modules.xhrim.entity.XhRim;
import com.jeeplus.modules.xhrim.service.XhRimService;

/**
 * 商品周边Controller
 * @author wujianbing
 * @version 2019-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/xhrim/xhRim")
public class XhRimController extends BaseController {

	@Autowired
	private XhRimService xhRimService;
	@Autowired
	private XhGoodsService xhGoodsService;
	@Autowired
	private UploadFileService uploadFileService;
	@ModelAttribute
	public XhRim get(@RequestParam(required=false) String id) {
		XhRim entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xhRimService.get(id);
		}
		if (entity == null){
			entity = new XhRim();
		}
		return entity;
	}
	
	/**
	 * 商品周边列表页面
	 */
	@RequiresPermissions("xhrim:xhRim:list")
	@RequestMapping(value = {"list", ""})
	public String list(XhRim xhRim, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XhRim> page = xhRimService.findPage(new Page<XhRim>(request, response), xhRim); 
		model.addAttribute("page", page);
		return "modules/xhrim/xhRimList";
	}

	/**
	 * 查看，增加，编辑商品周边表单页面
	 */
	@RequiresPermissions(value={"xhrim:xhRim:view","xhrim:xhRim:add","xhrim:xhRim:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(XhRim xhRim, Model model) {
		XhGoods xhGoods = new XhGoods();
		List<XhGoods> xlist=xhGoodsService.findList(xhGoods);
		model.addAttribute("xlist",xlist);
		model.addAttribute("xhRim", xhRim);
		return "modules/xhrim/xhRimForm";
	}

	/**
	 * 保存商品周边
	 */
	@RequiresPermissions(value={"xhrim:xhRim:add","xhrim:xhRim:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(XhRim xhRim, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) throws Exception{
		if (!beanValidator(model, xhRim)){
			return form(xhRim, model);
		}
		MultipartFile multipartFile = FileUtils.getMultipartFile(request, "imgNews");
		User user = UserUtils.getUser();
		UploadFile uploadFile = new UploadFile();
		if (multipartFile.getSize()>0){
			uploadFile = FileUtils.saveNomalFile(multipartFile, "imgNews", user);
			uploadFileService.saveAndReturnId(uploadFile);
			xhRim.setUploadFile(uploadFileService.get(uploadFile.getId()));
		}
		if(!xhRim.getIsNewRecord()){//编辑表单保存
			XhRim t = xhRimService.get(xhRim.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(xhRim, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			xhRimService.save(t);//保存
		}else{//新增表单保存
			xhRimService.save(xhRim);//保存
		}
		addMessage(redirectAttributes, "保存商品周边成功");
		return "redirect:"+Global.getAdminPath()+"/xhrim/xhRim/?repage";
	}
	
	/**
	 * 删除商品周边
	 */
	@RequiresPermissions("xhrim:xhRim:del")
	@RequestMapping(value = "delete")
	public String delete(XhRim xhRim, RedirectAttributes redirectAttributes) {
		xhRimService.delete(xhRim);
		addMessage(redirectAttributes, "删除商品周边成功");
		return "redirect:"+Global.getAdminPath()+"/xhrim/xhRim/?repage";
	}
	
	/**
	 * 批量删除商品周边
	 */
	@RequiresPermissions("xhrim:xhRim:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			xhRimService.delete(xhRimService.get(id));
		}
		addMessage(redirectAttributes, "删除商品周边成功");
		return "redirect:"+Global.getAdminPath()+"/xhrim/xhRim/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("xhrim:xhRim:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(XhRim xhRim, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "商品周边"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<XhRim> page = xhRimService.findPage(new Page<XhRim>(request, response, -1), xhRim);
    		new ExportExcel("商品周边", XhRim.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出商品周边记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhrim/xhRim/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("xhrim:xhRim:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<XhRim> list = ei.getDataList(XhRim.class);
			for (XhRim xhRim : list){
				try{
					xhRimService.save(xhRim);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条商品周边记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条商品周边记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入商品周边失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhrim/xhRim/?repage";
    }
	
	/**
	 * 下载导入商品周边数据模板
	 */
	@RequiresPermissions("xhrim:xhRim:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "商品周边数据导入模板.xlsx";
    		List<XhRim> list = Lists.newArrayList(); 
    		new ExportExcel("商品周边数据", XhRim.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhrim/xhRim/?repage";
    }
	
	
	

}