package com.gome.gmhx.controller.servicemanage;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.gome.gmhx.entity.HxServiceTicket;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.HxServiceTicketVO;
import com.gome.gmhx.service.servicemanage.HxServiceTicketCreateService;
import com.gome.gmhx.service.sysconfig.HxOrganizationService;

@Controller
@RequestMapping(value="/serviceTicketQuery")
public class HxServiceTicketQueryController {
	
	@Resource
	HxOrganizationService hxOrganizationService;
	
	@Resource
	HxServiceTicketCreateService serviceTicketCreateService;
	
	@RequestMapping(value="/serviceTicketQueryView")
	public String ServiceTicketCreateView(){
		return "servicemanage/ServiceTicketQuery/ServiceTicketQueryList";
	}
	
	@RequestMapping(value="/getServiceTicketPageList", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getServiceTicketPageList(Page page,HxServiceTicketVO serviceTicketVO,HttpServletRequest request,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_installDate_st,@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_installDate_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_saleDate_st,@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_saleDate_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_serviceDate_st,@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_serviceDate_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_checkDate_st,@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_checkDate_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_createDate_st,@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_createDate_end
			) throws Exception{
		Calendar calendar = Calendar.getInstance();
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		Map <String , Object> map = BeanMapUtils.convertBean(serviceTicketVO);
		map.put("mod_installDate_st", mod_installDate_st);
		if(mod_installDate_end!=null){
			calendar.setTime(mod_installDate_end);
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
			map.put("mod_installDate_end", calendar.getTime());
		}
		map.put("mod_saleDate_st", mod_saleDate_st);
		if(mod_saleDate_end!=null){
			calendar.setTime(mod_saleDate_end);
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
			map.put("mod_saleDate_end", calendar.getTime());
		}
		map.put("mod_serviceDate_st", mod_serviceDate_st);
		if(mod_serviceDate_end!=null){
			calendar.setTime(mod_serviceDate_end);
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
			map.put("mod_serviceDate_end", calendar.getTime());
		}
		map.put("mod_checkDate_st", mod_checkDate_st);
		if(mod_checkDate_end!=null){
			calendar.setTime(mod_checkDate_end);
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
			map.put("mod_checkDate_end", calendar.getTime());
		}
		map.put("mod_createDate_st", mod_createDate_st);
		if(mod_createDate_end!=null){
			calendar.setTime(mod_createDate_end);
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
			map.put("mod_createDate_end", calendar.getTime());
		}
		map.put("createMan", sysUser.getUserAccount());
		// 普通用户
		//map.put("customerType", "01");
		map.put("createOrganization", hxOrganizationService.getChild(sysUser.getCompanyId()));
		page.setParam(map);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(!"GMZB".equals(sysUser.getCompanyId())){
			list = serviceTicketCreateService.getServiceTicketPageList(page);
		}else{
			list = serviceTicketCreateService.getGMZBPageList(page);
		}
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/addServiceTicketView")
	public String addServiceTicketView(){
		return "servicemanage/ServiceTicketQuery/ServiceTicketAdd";
	}
	
	@RequestMapping(value="/saveServiceTicket", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String saveServiceTicket(HttpServletRequest request,@RequestBody HxServiceTicketVO serviceTicketVO){
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		try {
			String serviceId = serviceTicketCreateService.saveServiceTicket(serviceTicketVO,sysUser);
            return "{\"flag\" : \"success\",\"serviceId\" : \""+serviceId+"\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
	@RequestMapping(value="/serviceTicketView/{serviceId}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView serviceTicketQueryView(@PathVariable String serviceId){
		ModelAndView mav = new ModelAndView("servicemanage/ServiceTicketQuery/ServiceTicketShow");
		Map<String, Object> map = serviceTicketCreateService.getServiceTicketById(serviceId);
		mav.addObject("map", map);
		return mav;
	}
	
	@RequestMapping(value="/serviceTicketDetailView/{serviceId}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView serviceTicketDetailView(@PathVariable String serviceId){
		ModelAndView mav = new ModelAndView("servicemanage/ServiceTicketQuery/ServiceTicketDetailShow");
		Map<String, Object> map = serviceTicketCreateService.getServiceTicketById(serviceId);
		mav.addObject("map", map);
		return mav;
	}
	
	@RequestMapping(value="/updateServiceTicketView/{serviceId}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView updateServiceTicketView(@PathVariable String serviceId){
		ModelAndView mav = new ModelAndView("servicemanage/ServiceTicketQuery/ServiceTicketUpdate");
		Map<String, Object> map = serviceTicketCreateService.getServiceTicketById(serviceId);
		mav.addObject("map", map);
		return mav;
	}
	
	
	@RequestMapping(value="/updateServiceTicket", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String updateServiceTicket(HttpServletRequest request,HxServiceTicket serviceTicket){
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		serviceTicket.setAlterManS(sysUser.getUserAccount());
		serviceTicket.setAlterOrganizationS(sysUser.getCompanyId());
		serviceTicket.setAlterTimeS(new Date());
		try {
			serviceTicketCreateService.updateServiceTicket(serviceTicket);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
	@RequestMapping(value="/serviceTicketSendView/{serviceId}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView serviceTicketSendView(@PathVariable String serviceId){
		ModelAndView mav = new ModelAndView("servicemanage/ServiceTicketQuery/ServiceTicketSendShow");
		Map<String, Object> map = serviceTicketCreateService.getServiceTicketById(serviceId);
		mav.addObject("map", map);
		return mav;
	}
	
	@RequestMapping(value="/serviceTicketSend", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String serviceTicketSend(HxServiceTicket serviceTicket){
		// 发送
		serviceTicket.setServiceStatus("02");
		try {
			serviceTicketCreateService.updateServiceTicket(serviceTicket);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
	
	@RequestMapping(value="/deleteServiceTicket/{serviceId}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String deleteServiceTicket(@PathVariable String serviceId){
		try {
			serviceTicketCreateService.deleteServiceTicketById(serviceId);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
	@RequestMapping(value="/exportExcel", produces = "text/plain;charset=utf-8")
	public ModelAndView exportExcel(Page page,HxServiceTicketVO serviceTicketVO,HttpServletRequest request,String tableField, String tableHeader, HttpServletResponse response,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_installDate_st,@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_installDate_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_saleDate_st,@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_saleDate_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_serviceDate_st,@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_serviceDate_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_checkDate_st,@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_checkDate_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_createDate_st,@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_createDate_end
			) throws Exception{
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		Map <String , Object> map = BeanMapUtils.convertBean(serviceTicketVO);
		map.put("mod_installDate_st", mod_installDate_st);
		map.put("mod_installDate_end", mod_installDate_end);
		map.put("mod_saleDate_st", mod_saleDate_st);
		map.put("mod_saleDate_end", mod_saleDate_end);
		map.put("mod_serviceDate_st", mod_serviceDate_st);
		map.put("mod_serviceDate_end", mod_serviceDate_end);
		map.put("mod_checkDate_st", mod_checkDate_st);
		map.put("mod_checkDate_end", mod_checkDate_end);
		map.put("mod_createDate_st", mod_createDate_st);
		map.put("mod_createDate_end", mod_createDate_end);
		// 普通用户
		map.put("customerType", "01");
		map.put("createOrganization", hxOrganizationService.getChild(sysUser.getCompanyId()));
		page.setParam(map);
		List<Map<String, Object>> list = serviceTicketCreateService.getServiceTicketPageList(page);
		new DecoderUtil<HxServiceTicketVO>().decodeURI(serviceTicketVO);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		header = header.replaceAll("<br/>", "");
		ViewExcel viewExcel = new ViewExcel("服务单导出" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
		return new ModelAndView(viewExcel);
	}
}
