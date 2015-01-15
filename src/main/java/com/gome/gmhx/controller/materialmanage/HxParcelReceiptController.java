package com.gome.gmhx.controller.materialmanage;

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
import com.gome.gmhx.entity.HxParcelDelivery;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.service.materialmanage.HxParcelReceiptService;

@Controller
@RequestMapping(value="/hxParcelReceipt")
public class HxParcelReceiptController {
	@Resource
	private HxParcelReceiptService hxParcelReceiptService;
	
	@RequestMapping(value="/hxParcelReceiptView")
	public String hxParcelReceiptView(){
		return "materialmanage/hxParcelReceipt/hxParcelReceiptList";
	}
	                        
	@RequestMapping(value="/getHxParcelReceiptPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxParcelReceiptPageList(HttpServletRequest request,Page page, HxParcelDelivery hxParcelReceipt) throws Exception{
//		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
//		hxParcelReceipt.setFounders(sysUser.getUserAccount());
//		hxParcelReceipt.setFittingPositionId(sysUser.getFittingPositionId());
		Map <String , Object> map =BeanMapUtils.convertBean(hxParcelReceipt);
		//map.put("receiveUnit", sysUser.getFittingOrgId());
		map.put("sendDate_sta", request.getParameter("sendDate_sta"));
		map.put("sendDate_sta", request.getParameter("sendDate_sta"));
		page.setParam(map);
		List<Map<String, Object>> list = hxParcelReceiptService.getHxParcelReceiptPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/addView")//预留
	public String addView(){
		return "materialmanage/hxParcelReceipt/hxParcelReceiptAdd";
	}
	
	@RequestMapping(value="/addHxParcelReceipt")//预留
	@ResponseBody
	public String addHxParcelReceipt(HxParcelDelivery hxParcelReceipt, HttpServletRequest request) {
        try {	
        	hxParcelReceiptService.addHxParcelReceipt(hxParcelReceipt);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	@RequestMapping(value="/updateView/{orgId}")//预留
	public ModelAndView updateView(@PathVariable(value = "orgId") String orgId){
		ModelAndView mav = new ModelAndView("materialmanage/hxParcelReceipt/hxParcelReceiptUpdate");
		HxParcelDelivery hxParcelReceipt = hxParcelReceiptService.getHxParcelReceiptById(orgId);
		mav.addObject("hxParcelReceipt", hxParcelReceipt);
		return mav;
	}
	
	@RequestMapping(value="/updateHxParcelReceipt")//收货逻辑
	@ResponseBody
	public String updateHxParcelReceipt(HxParcelDelivery delivery, HttpServletRequest request){
		try {
			this.hxParcelReceiptService.updateReceiveStock(delivery);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"flag\" : \"failure\"}";
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(HxParcelDelivery hxParcelReceipt, String tableField, String tableHeader, HttpServletRequest request, HttpServletResponse response) throws Exception{
		new DecoderUtil<HxParcelDelivery>().decodeURI(hxParcelReceipt);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constrants.USER_INFO);
		Map <String , Object> map =BeanMapUtils.convertBean(hxParcelReceipt);
		map.put("sendDate_sta", request.getParameter("sendDate_sta"));
		map.put("sendDate_end", request.getParameter("sendDate_end"));
		map.put("founders", sysUser.getUserAccount());
		map.put("fittingPositionId", sysUser.getSysPositionId());
		map.put("fittingOrgId", sysUser.getCompanyId());
		List<Map<String, Object>> list = hxParcelReceiptService.getHxParcelReceiptExport(map);
		ViewExcel viewExcel = new ViewExcel("导出实例" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
		return new ModelAndView(viewExcel);
	}
	
	
	@RequestMapping(value="/showView/{parcelCode}")
	public ModelAndView showView(@PathVariable(value = "parcelCode") String parcelCode){
		ModelAndView mav = new ModelAndView("materialmanage/hxParcelReceipt/hxParcelReceiptShow");
		HxParcelDelivery hxParcelReceipt= hxParcelReceiptService.getShowById(parcelCode);
		mav.addObject("parcelCode", parcelCode);
		mav.addObject("hxParcelReceipt", hxParcelReceipt);
		return mav;
	}
	
	@RequestMapping(value="/showParcelView/{parcelCode}")
	public ModelAndView showParcelView(@PathVariable(value = "parcelCode") String orgId){
		ModelAndView mav = new ModelAndView("materialmanage/hxParcelReceipt/hxParcelShow");
		HxParcelDelivery hxParcelReceipt= hxParcelReceiptService.getShowById(orgId);
		mav.addObject("hxParcelReceipt", hxParcelReceipt);
		return mav;
	}

}
