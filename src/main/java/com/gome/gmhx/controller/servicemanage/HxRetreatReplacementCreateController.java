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
import com.gome.gmhx.service.servicemanage.HxRetreatReplacementCreateService;
import com.gome.gmhx.service.sysconfig.HxOrganizationService;

@Controller
@RequestMapping(value="/retreatReplacementCreate")
public class HxRetreatReplacementCreateController {
	
	@Resource
	HxOrganizationService hxOrganizationService;
	
	@Resource
	private HxRetreatReplacementCreateService hxRetreatReplacementCreateService;
	
	@RequestMapping(value="/retreatReplacementCreateView")
	public String ServiceTicketCreateView(){
		return "servicemanage/RetreatReplacementCreate/RetreatReplacementCreateList";
	}
	
	@RequestMapping(value="/addview")
	public String addview(){
		return "servicemanage/RetreatReplacementCreate/RetreatReplacementCreateAdd";
	}
	
	@RequestMapping(value="/getRetreatReplacementCreateList", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getRetreatReplacementCreateList(Page page,HxServiceRetreatReplacement retreatReplacement,HttpServletRequest request,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date buyDateStartTime,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date buyDateEndTime,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date applyDateStartTime,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date applyDateEndTime,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date installDateStartTime,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date installDateEndTime
			) throws Exception{
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		Map <String , Object> map =BeanMapUtils.convertBean(retreatReplacement); 
		map.put("buyDateStartTime", buyDateStartTime);
		map.put("buyDateEndTime", buyDateEndTime);
		map.put("applyDateStartTime", applyDateStartTime);
		map.put("applyDateEndTime", applyDateEndTime);
		map.put("installDateStartTime", installDateStartTime);
		map.put("installDateEndTime", installDateEndTime);
		map.put("createOrganization", hxOrganizationService.getChild(sysUser.getCompanyId()));
		page.setParam(map);
		List<Map<String, Object>> list = hxRetreatReplacementCreateService.getRetreatReplacementCreatePageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/saveRetreatReplacement", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String saveRetreatReplacement(HttpServletRequest request,@RequestBody HxServiceRetreatReplacement retreatReplacement){
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		try {
			String data = hxRetreatReplacementCreateService.saveRetreatReplacement(retreatReplacement,sysUser);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
	
	@RequestMapping(value="/retreatReplacementView/{applyId}", produces = "text/plain;charset=utf-8")
	public ModelAndView retreatReplacementView(@PathVariable String applyId){
		ModelAndView mav = new ModelAndView("servicemanage/RetreatReplacementCreate/RetreatReplacementCreateShow");
		HxServiceRetreatReplacement sr = hxRetreatReplacementCreateService.getRetreatReplacementById(applyId);
		mav.addObject("sr", sr);
		return mav;
	}
	
	
	@RequestMapping(value="/retreatReplacementUpdateView/{applyId}", produces = "text/plain;charset=utf-8")
	public ModelAndView retreatReplacementUpdate(@PathVariable String applyId){
		ModelAndView mav = new ModelAndView("servicemanage/RetreatReplacementCreate/RetreatReplacementUpdate");
		HxServiceRetreatReplacement sr = hxRetreatReplacementCreateService.getRetreatReplacementById(applyId);
		mav.addObject("sr", sr);
		return mav;
	}
	
	@RequestMapping(value="/retreatReplacementUpdate", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String retreatReplacementUpdate(HttpServletRequest request,@RequestBody HxServiceRetreatReplacement retreatReplacement){
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		try {
			String data = hxRetreatReplacementCreateService.updateRetreatReplacement(retreatReplacement,sysUser);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
	@RequestMapping(value="/deleteServiceTicket/{serviceId}", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String deleteServiceTicket(@PathVariable String serviceId){
		try {
			hxRetreatReplacementCreateService.deleteRetreatReplacement(serviceId);
			return "{\"flag\" : \"success\"}";
		} catch (Exception e){
			e.printStackTrace();
			return "{\"flag\" : \"failure\"}";
		}
	}
	
	@RequestMapping(value="/sendServiceTicket/{serviceId}", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String sendServiceTicket(@PathVariable String serviceId){
		try {
			hxRetreatReplacementCreateService.sendServiceTicket(serviceId);
			return "{\"flag\" : \"success\"}";
		} catch (Exception e){
			e.printStackTrace();
			return "{\"flag\" : \"failure\"}";
		}
	}
	
	//---------- 退换机申请单查询
	
	@RequestMapping(value="/retreatReplacementQueryView")
	public String retreatReplacementQueryView(){
		return "servicemanage/RetreatReplacementCreate/RetreatReplacementQueryList";
	}
	
	@RequestMapping(value="/retreatReplacementQueryShow/{applyId}", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView retreatReplacementQueryShow(@PathVariable String applyId){
		ModelAndView mav = new ModelAndView("servicemanage/RetreatReplacementCreate/RetreatReplacementQueryShow");
		HxServiceRetreatReplacement sr = hxRetreatReplacementCreateService.getRetreatReplacementById(applyId);
		mav.addObject("sr", sr);
		return mav;
	}
	
	@RequestMapping(value="/exportExcel", produces = "text/plain;charset=utf-8")
	public ModelAndView exportExcel(Page page,HxServiceRetreatReplacement retreatReplacement,HttpServletRequest request,String tableField, String tableHeader, HttpServletResponse response,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date buyDateStartTime,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date buyDateEndTime,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date applyDateStartTime,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date applyDateEndTime,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date installDateStartTime,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date installDateEndTime
			) throws Exception{
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		Map <String , Object> map =BeanMapUtils.convertBean(retreatReplacement); 
		map.put("buyDateStartTime", buyDateStartTime);
		map.put("buyDateEndTime", buyDateEndTime);
		map.put("applyDateStartTime", applyDateStartTime);
		map.put("applyDateEndTime", applyDateEndTime);
		map.put("installDateStartTime", installDateStartTime);
		map.put("installDateEndTime", installDateEndTime);
		map.put("createOrganization", hxOrganizationService.getChild(sysUser.getCompanyId()));
		page.setParam(map);
		List<Map<String, Object>> list = hxRetreatReplacementCreateService.getRetreatReplacementCreatePageList(page);
		new DecoderUtil<HxServiceRetreatReplacement>().decodeURI(retreatReplacement);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		header = header.replaceAll("<br/>", "");
		ViewExcel viewExcel = new ViewExcel("退换机申请单导出" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
		return new ModelAndView(viewExcel);
	}
	
}
