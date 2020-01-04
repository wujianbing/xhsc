/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.xhfloor.web;

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
import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.utils.DateUtils;
import com.jeeplus.common.utils.FileUtils;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.utils.excel.ExportExcel;
import com.jeeplus.common.utils.excel.ImportExcel;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.sys.entity.UploadFile;
import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.service.UploadFileService;
import com.jeeplus.modules.sys.utils.UserUtils;
import com.jeeplus.modules.xhfloor.entity.XhFloor;
import com.jeeplus.modules.xhfloor.service.XhFloorService;

/**
 * 楼层选择Controller
 * @author wujianbing
 * @version 2019-04-18
 */
@Controller
@RequestMapping(value = "${adminPath}/xhfloor/xhFloor")
public class XhFloorController extends BaseController {

	@Autowired
	private XhFloorService xhFloorService;
	@Autowired
	private UploadFileService uploadFileService;
	
	@ModelAttribute
	public XhFloor get(@RequestParam(required=false) String id) {
		XhFloor entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xhFloorService.get(id);
		}
		if (entity == null){
			entity = new XhFloor();
		}
		return entity;
	}
	
	/**
	 * 楼层选择列表页面
	 */
	@RequiresPermissions("xhfloor:xhFloor:list")
	@RequestMapping(value = {"list", ""})
	public String list(XhFloor xhFloor, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XhFloor> page = xhFloorService.findPage(new Page<XhFloor>(request, response), xhFloor); 
		model.addAttribute("page", page);
		return "modules/xhfloor/xhFloorList";
	}

	/**
	 * 查看，增加，编辑楼层选择表单页面
	 */
	@RequiresPermissions(value={"xhfloor:xhFloor:view","xhfloor:xhFloor:add","xhfloor:xhFloor:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(XhFloor xhFloor, Model model) {
		model.addAttribute("xhFloor", xhFloor);
		return "modules/xhfloor/xhFloorForm";
	}

	/**
	 * 保存楼层选择
	 */
	@RequiresPermissions(value={"xhfloor:xhFloor:add","xhfloor:xhFloor:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(XhFloor xhFloor, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) throws Exception{
		if (!beanValidator(model, xhFloor)){
			return form(xhFloor, model);
		}
		MultipartFile multipartFile = FileUtils.getMultipartFile(request, "imgNews");
		User user = UserUtils.getUser();
		UploadFile uploadFile = new UploadFile();
		if (multipartFile.getSize()>0){
			uploadFile = FileUtils.saveNomalFile(multipartFile, "imgNews", user);
			uploadFileService.saveAndReturnId(uploadFile);
			xhFloor.setUploadFile(uploadFileService.get(uploadFile.getId()));
		}
		if(!xhFloor.getIsNewRecord()){//编辑表单保存
			XhFloor t = xhFloorService.get(xhFloor.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(xhFloor, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			xhFloorService.save(t);//保存
		}else{//新增表单保存
			xhFloorService.save(xhFloor);//保存
		}
		addMessage(redirectAttributes, "保存楼层选择成功");
		return "redirect:"+Global.getAdminPath()+"/xhfloor/xhFloor/?repage";
	}
	
	/**
	 * 删除楼层选择
	 */
	@RequiresPermissions("xhfloor:xhFloor:del")
	@RequestMapping(value = "delete")
	public String delete(XhFloor xhFloor, RedirectAttributes redirectAttributes) {
		xhFloorService.delete(xhFloor);
		addMessage(redirectAttributes, "删除楼层选择成功");
		return "redirect:"+Global.getAdminPath()+"/xhfloor/xhFloor/?repage";
	}
	
	/**
	 * 批量删除楼层选择
	 */
	@RequiresPermissions("xhfloor:xhFloor:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			xhFloorService.delete(xhFloorService.get(id));
		}
		addMessage(redirectAttributes, "删除楼层选择成功");
		return "redirect:"+Global.getAdminPath()+"/xhfloor/xhFloor/?repage";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("xhfloor:xhFloor:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(XhFloor xhFloor, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "楼层选择"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<XhFloor> page = xhFloorService.findPage(new Page<XhFloor>(request, response, -1), xhFloor);
    		new ExportExcel("楼层选择", XhFloor.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出楼层选择记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhfloor/xhFloor/?repage";
    }

	/**
	 * 导入Excel数据

	 */
	@RequiresPermissions("xhfloor:xhFloor:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<XhFloor> list = ei.getDataList(XhFloor.class);
			for (XhFloor xhFloor : list){
				try{
					xhFloorService.save(xhFloor);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条楼层选择记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条楼层选择记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入楼层选择失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhfloor/xhFloor/?repage";
    }
	
	/**
	 * 下载导入楼层选择数据模板
	 */
	@RequiresPermissions("xhfloor:xhFloor:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "楼层选择数据导入模板.xlsx";
    		List<XhFloor> list = Lists.newArrayList(); 
    		new ExportExcel("楼层选择数据", XhFloor.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/xhfloor/xhFloor/?repage";
    }
	
	
	

}