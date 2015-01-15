package com.gome.gmhx.controller.servicemanage;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.Constrants;
import com.gome.common.page.Page;
import com.gome.common.util.BeanMapUtils;
import com.gome.common.util.DecoderUtil;
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.HxServiceCustomer;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.HxServiceTicketVO;
import com.gome.gmhx.service.servicemanage.HxHistoryTicketService;
import com.gome.gmhx.service.sysconfig.HxOrganizationService;

@Controller
@RequestMapping(value="/historyTicketQuery")
public class HxHistoryTicketQueryController {
	@Resource
	HxHistoryTicketService hxHistoryTicketService;
	
	@Resource
	HxOrganizationService hxOrganizationService;
	
	@RequestMapping(value="/historyTicketQueryView")
	public String installProjectView(){
		return "servicemanage/HistoryTicket/HistoryTicketList";
	}
	
	@RequestMapping(value="/getHistoryTicket", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHistoryTicket(Page page, HxServiceTicketVO serviceTicketVO,HttpServletRequest request,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_createDate_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_createDate_end,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_serviceDate_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_serviceDate_end
			) throws Exception{
		Calendar calendar = Calendar.getInstance();
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		Map<String, Object> map = BeanMapUtils.convertBean(serviceTicketVO);
		map.put("mod_serviceDate_st", mod_serviceDate_st);
		map.put("mod_serviceDate_end", mod_serviceDate_end);
		
		map.put("mod_createDate_st", mod_createDate_st);
		if(mod_createDate_end!=null){
			calendar.setTime(mod_createDate_end);
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
			map.put("mod_createDate_end", calendar.getTime());
		}
		//map.put("serviceType", "01");
		//map.put("serviceStatus", "04");
		map.put("createOrganization", hxOrganizationService.getChild(sysUser.getCompanyId()));
		page.setParam(map);
		List<Map<String, Object>> list = hxHistoryTicketService.getHistoryTicketPageList(page);
		//return JsonUtil.writeListToDataGrid(list.size(), list);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/serviceTicketView/{serviceId}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView serviceTicketView(@PathVariable String serviceId){
		ModelAndView mav = new ModelAndView("servicemanage/HistoryTicket/ServiceTicketShow");
		Map<String, Object> map = hxHistoryTicketService.getServiceTicketById(serviceId);
		mav.addObject("map", map);
		return mav;
	}
}
