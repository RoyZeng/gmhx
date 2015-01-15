package com.gome.gmhx.controller.servicemanage;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.Constrants;
import com.gome.common.bean.ViewExcel;
import com.gome.common.page.Page;
import com.gome.common.util.BeanMapUtils;
import com.gome.common.util.DateUtils;
import com.gome.common.util.DecoderUtil;
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.HxServiceRetreatReplacement;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.HxServiceTicketVO;
import com.gome.gmhx.service.servicemanage.HxInstallProjectService;

@Controller
@RequestMapping(value="/installProject")
public class HxInstallProjectController {
	
	@Resource
	private HxInstallProjectService installProjectService;
	
	@RequestMapping(value="/installProjectView")
	public String installProjectView(){
		return "servicemanage/InstallProject/InstallProjectList";
	}

	@RequestMapping(value="/getInstallReceiptPageList", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getInstallReceiptPageList(Page page,HxServiceTicketVO serviceTicketVO,HttpServletRequest request,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_createTime_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_createTime_end
			) throws Exception{
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		Map <String , Object> map =BeanMapUtils.convertBean(serviceTicketVO);
		map.put("mod_createTime_st", mod_createTime_st);
		map.put("mod_createTime_end", mod_createTime_end);
		// 工程机用户
		map.put("customerType", "02");
		map.put("createMan", sysUser.getUserAccount());
		page.setParam(map);
		List<Map<String, Object>> list = installProjectService.getServiceInstallReceiptPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/addview")
	@ResponseBody
	public ModelAndView addView(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("servicemanage/InstallProject/AddInstallProject");
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		mav.addObject("usr", sysUser);
		return mav;
	}
	
	@RequestMapping(value="/saveInstallProject", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String saveInstallProject(HttpServletRequest request,@RequestBody HxServiceTicketVO serviceTicketVO){
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		try {
			String message = installProjectService.saveInstallProject(serviceTicketVO,sysUser);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
	@RequestMapping(value="/installProjectShow/{serviceId}")
	@ResponseBody
	public ModelAndView installProjectShow(@PathVariable String serviceId){
		ModelAndView mav = new ModelAndView("servicemanage/InstallProject/InstallProjectShow");
		Map<String,Object> map = installProjectService.getInstallProjectById(serviceId);
		mav.addObject("map", map);
		return mav;
	}
	
	@RequestMapping(value="/getProjects/{serviceId}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getProjects(@PathVariable String serviceId) throws Exception{
		List<Map<String, Object>> list = installProjectService.getProjects(serviceId);
		return JsonUtil.writeListToDataGrid(list.size(), list);
	}
	
	@RequestMapping(value="/installProjectUpdateView/{serviceId}")
	@ResponseBody
	public ModelAndView installProjectUpdateView(@PathVariable String serviceId){
		ModelAndView mav = new ModelAndView("servicemanage/InstallProject/InstallProjectUpdate");
		Map<String,Object> map = installProjectService.getInstallProjectById(serviceId);
		mav.addObject("map", map);
		return mav;
	}
	
	@RequestMapping(value="/installProjectUpdate", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String installProjectUpdate(HttpServletRequest request,@RequestBody HxServiceTicketVO serviceTicketVO){
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		try {
			String message = installProjectService.updateInstallProject(serviceTicketVO,sysUser);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
	@RequestMapping(value="/deleteInstallProject", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String deleteInstallProject(String ids){
		 String[] serviceIds = ids.split(",");
		 for(String serviceId : serviceIds){
			 installProjectService.deleteServiceInstallReceipt(serviceId);
		 }
		return "{\"flag\" : \"success\"}";
	}
	
	//---------- 工程机查询
	
	@RequestMapping(value="/installProjectQueryView")
	public String installProjectQueryView(){
		return "servicemanage/InstallProject/InstallProjectQueryList";
	}
	
	@RequestMapping(value="/installProjectQueryShow/{serviceId}", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView installProjectQueryShow(@PathVariable String serviceId){
		ModelAndView mav = new ModelAndView("servicemanage/InstallProject/InstallProjectQueryShow");
		Map<String,Object> map = installProjectService.getInstallProjectById(serviceId);
		mav.addObject("map", map);
		return mav;
	}
	
	@RequestMapping(value="/exportExcel", produces = "text/plain;charset=utf-8")
	public ModelAndView exportExcel(Page page,HxServiceTicketVO serviceTicketVO,HttpServletRequest request,String tableField, String tableHeader, HttpServletResponse response,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_createTime_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_createTime_end
			) throws Exception{
		Map <String , Object> map =BeanMapUtils.convertBean(serviceTicketVO);
		map.put("mod_createTime_st", mod_createTime_st);
		map.put("mod_createTime_end", mod_createTime_end);
		// 工程机用户
		map.put("customerType", "02");
		page.setParam(map);
		List<Map<String, Object>> list = installProjectService.getServiceInstallReceiptPageList(page);
		new DecoderUtil<HxServiceTicketVO>().decodeURI(serviceTicketVO);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		header = header.replaceAll("<br/>", "");
		ViewExcel viewExcel = new ViewExcel("工程机申请单导出" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
		return new ModelAndView(viewExcel);
	}
}





















