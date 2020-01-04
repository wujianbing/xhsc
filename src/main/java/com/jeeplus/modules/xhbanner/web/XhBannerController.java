/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhbanner.web;

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
import com.jeeplus.modules.xhbanner.entity.XhBanner;
import com.jeeplus.modules.xhbanner.service.XhBannerService;
import com.jeeplus.modules.xhgoods.entity.XhGoods;
import com.jeeplus.modules.xhgoods.service.XhGoodsService;

/**
 * 鲜花轮播图Controller
 * @author wujianbing
 * @version 2019-04-04
 */
@Controller
@RequestMapping(value = "${adminPath}/xhbanner/xhBanner")
public class XhBannerController extends BaseController {

	@Autowired
	private XhBannerService xhBannerService;
	@Autowired
	private XhGoodsService xhGoodsService;
	@Autowired
	private UploadFileService uploadFileService;
	@ModelAttribute
	public XhBanner get(@RequestParam(required=false) String id) {
		XhBanner entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xhBannerService.get(id);
		}
		if (entity == null){
			entity = new XhBanner();
		}
		return entity;
	}
	
	/**
	 * 鲜花轮播图列表页面
	 */
	@RequiresPermissions("xhbanner:xhBanner:list")
	@RequestMapping(value = {"list", ""})
	public String list(XhBanner xhBanner, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XhBanner> page = xhBannerService.findPage(new Page<XhBanner>(request, response), xhBanner); 
		model.addAttribute("page", page);
		return "modules/xhbanner/xhBannerList";
	}

	/**
	 * 查看，增加，编辑鲜花轮播图表单页面
	 */
	@RequiresPermissions(value={"xhbanner:xhBanner:view","xhbanner:xhBanner:add","xhbanner:xhBanner:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(XhBanner xhBanner, Model model) {
		XhGoods xhGoods = new XhGoods();
		List<XhGoods> xlist=xhGoodsService.findList(xhGoods);
		model.addAttribute("xlist",xlist);
		model.addAttribute("xhBanner", xhBanner);
		return "modules/xhbanner/xhBannerForm";
	}

	/**
	 * 保存鲜花轮播图
	 */
	@RequiresPermissions(value={"xhbanner:xhBanner:add","xhbanner:xhBanner:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(XhBanner xhBanner, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) throws Exception{
		if (!beanValidator(model, xhBanner)){
			return form(xhBanner, model);
		}
		MultipartFile multipartFile = FileUtils.getMultipartFile(request, "imgNews");
		User user = UserUtils.getUser();
		UploadFile uploadFile = new UploadFile();
		if (multipartFile.getSize()>0){
			uploadFile = FileUtils.saveNomalFile(multipartFile, "imgNews", user);
			uploadFileService.saveAndReturnId(uploadFile);
			xhBanner.setUploadFile(uploadFileService.get(uploadFile.getId()));
		}
		
		if(!xhBanner.getIsNewRecord()){//编辑表单保存
			XhBanner t = xhBannerService.get(xhBanner.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(xhBanner, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			xhBannerService.save(t);//保存
		}else{//新增表单保存
			xhBannerService.save(xhBanner);//保存
		}
		addMessage(redirectAttributes, "保存鲜花轮播图成功");
		return "redirect:"+Global.getAdminPath()+"/xhbanner/xhBanner/?repage";
	}
	
	/**
	 * 删除鲜花轮播图
	 */
	@RequiresPermissions("xhbanner:xhBanner:del")
	@RequestMapping(value = "delete")
	public String delete(XhBanner xhBanner, RedirectAttributes redirectAttributes) {
		xhBannerService.delete(xhBanner);
		addMessage(redirectAttributes, "删除鲜花轮播图成功");
		return "redirect:"+Global.getAdminPath()+"/xhbanner/xhBanner/?repage";
	}
	
	/**
	 * 批量删除鲜花轮播图
	 */
	@RequiresPermissions("xhbanner:xhBanner:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			xhBannerService.delete(xhBannerService.get(id));
		}
		addMessage(redirectAttributes, "删除鲜花轮播图成功");
		return "redirect:"+Global.getAdminPath()+"/xhbanner/xhBanner/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("xhbanner:xhBanner:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(XhBanner xhBanner, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "鲜花轮播图"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<XhBanner> page = xhBannerService.findPage(new Page<XhBanner>(request, response, -1), xhBanner);
    		new ExportExcel("鲜花轮播图", XhBanner.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出鲜花轮播图记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhbanner/xhBanner/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("xhbanner:xhBanner:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<XhBanner> list = ei.getDataList(XhBanner.class);
			for (XhBanner xhBanner : list){
				try{
					xhBannerService.save(xhBanner);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条鲜花轮播图记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条鲜花轮播图记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入鲜花轮播图失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhbanner/xhBanner/?repage";
    }
	
	/**
	 * 下载导入鲜花轮播图数据模板
	 */
	@RequiresPermissions("xhbanner:xhBanner:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "鲜花轮播图数据导入模板.xlsx";
    		List<XhBanner> list = Lists.newArrayList(); 
    		new ExportExcel("鲜花轮播图数据", XhBanner.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhbanner/xhBanner/?repage";
    }
	
	
	

}