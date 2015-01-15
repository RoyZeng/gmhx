package com.gome.gmhx.controller.servicemanage;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.Constrants;
import com.gome.common.page.Page;
import com.gome.common.util.BeanMapUtils;
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.HxServiceProgressInfo;
import com.gome.gmhx.entity.HxServiceTicket;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.HxServiceTicketVO;
import com.gome.gmhx.service.servicemanage.HxServiceTicketCheckService;
import com.gome.gmhx.service.sysconfig.HxOrganizationService;

@Controller
@RequestMapping(value="/serviceTicketCheck")
public class HxServiceTicketCheckController {
	
	@Resource
	HxServiceTicketCheckService serviceTicketCheckService;
	
	@Resource
	HxOrganizationService hxOrganizationService;  

	@RequestMapping(value="/serviceTicketCheckView")
	public String ServiceTicketCreateView(){
		return "servicemanage/ServiceTicketCheck/ServiceTicketCheckList";
	}
	
	@RequestMapping(value="/getServiceTicketCheckList")
	public String GetServiceTicketCheckList(){
		return null;
	}
	
	@RequestMapping(value="/getServiceTicketCheckPageList",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getServiceTicketCheckPagList(Page page,HxServiceTicketVO serviceTicketVO,HttpServletRequest request) throws Exception{
		Map<String, Object> map = BeanMapUtils.convertBean(serviceTicketVO);
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		// 个人服务
		map.put("customerType", "01");
		// 已经确认
		map.put("serviceStatus", "05");
		map.put("createOrganization", hxOrganizationService.getChild(sysUser.getCompanyId()));
		page.setParam(map);
		List<Map<String, Object>> list = serviceTicketCheckService.getRepairReceiptPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/serviceTicketCheckView/{serviceId}",produces="text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView serviceTicketCheckView(@PathVariable String serviceId){
		ModelAndView mav = new ModelAndView("servicemanage/ServiceTicketCheck/ServiceTicketCheck");
		Map<String, Object> map = serviceTicketCheckService.getServiceTicketById(serviceId);
		mav.addObject("map", map);
		return mav;
	}
	
	@RequestMapping(value="/serviceTicketView/{serviceId}",produces="text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView serviceTicketView(@PathVariable String serviceId){
		ModelAndView mav = new ModelAndView("servicemanage/ServiceTicketCheck/ServiceTicketShow");
		Map<String, Object> map = serviceTicketCheckService.getServiceTicketById(serviceId);
		mav.addObject("map", map);
		return mav;
	}
	
	@RequestMapping(value="/saveServiceTicketCheck",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String saveServiceTicketCheck(HttpServletRequest request,HxServiceTicket serviceTicket){
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		try {
			serviceTicketCheckService.saveServiceTicketCheck(serviceTicket,sysUser);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
	@RequestMapping(value="/addProgress")
	@ResponseBody
	public String addProgress(HttpServletRequest request,@RequestBody HxServiceTicketVO serviceTicketVO){
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		List<HxServiceProgressInfo>  progressInfos = serviceTicketVO.getServiceProgressInfos();
		try {
			serviceTicketCheckService.saveServiceProgress(progressInfos,sysUser);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
}
