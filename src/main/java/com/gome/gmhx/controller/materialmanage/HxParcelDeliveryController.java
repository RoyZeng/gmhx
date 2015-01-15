package com.gome.gmhx.controller.materialmanage;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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
import com.gome.common.util.JsonHelper;
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.HxOrganization;
import com.gome.gmhx.entity.HxParcelDelivery;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.HxParcelDeliveryVO;
import com.gome.gmhx.service.materialmanage.HxMaterialService;
import com.gome.gmhx.service.materialmanage.HxParcelDeliveryService;

@Controller
@RequestMapping(value = "/ParcelDelivery")
public class HxParcelDeliveryController {
	@Resource
	private HxParcelDeliveryService parcelDeliveryService;
	@Resource
	private HxMaterialService hxMaterialService;

	@RequestMapping(value = "/ParcelDeliveryView")
	private String ParcelDeliveryView() {

		return "materialmanage/hxParcelDelivery/hxParcelDeliveryList";

	}

	@RequestMapping(value = "/getParcelDeliveryPageList", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getParcelDeliveryPageList(Page page, HxParcelDelivery parcel,	HttpServletRequest request)
			throws Exception {
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		parcel.setFounders(sysUser.getUserAccount());
		parcel.setFittingPositionId(sysUser.getSysPositionId());
		Map<String, Object> map = BeanMapUtils.convertBean(parcel);
//		map.put("billsCode", sysUser.getFittingOrgId());
		map.put("updateDate_sta", request.getParameter("updateDate_sta"));
		map.put("updateDate_end", request.getParameter("updateDate_end"));
		page.setParam(map);
		List<Map<String, Object>> list = parcelDeliveryService.getParcelDeliveryPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}

	/*
	 * 获取邮包编号
	 */
	private synchronized String getParcelCode(String number) {
		StringBuilder sb = new StringBuilder("BG_");
		String date = DateUtils.formatDateTime(new Date(), DateUtils.EIGHT_STYLE_DATE_FORMAT);
		sb.append(date.substring(2, 8));
		sb.append(parcelDeliveryService.getParcelCode(number));
		return sb.toString();
	}

	@RequestMapping(value = "/addView")
	public String addView() {
		return "materialmanage/hxParcelDelivery/hxParcelDeliveryAdd";
	}

	/**
	 * 暂存邮包
	 */
	@RequestMapping(value = "addHxParcelDelivery")
	@ResponseBody
	public String addHxParcelDelivery(HxParcelDeliveryVO parcel, HttpServletRequest request) {

		SysUser sysuser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		parcel.setOrgId(sysuser.getCompanyId());
		parcel.setFounderDate(new Date());
		parcel.setParcelCode(getParcelCode(sysuser.getCompanyId()));
		String createPerson = sysuser.getUserAccount();
		parcel.setFounders(createPerson);
		parcel.setFounderDate(new Date());
		parcel.setSendUnit(sysuser.getCompanyId());
		parcel.setFittingPositionId(sysuser.getSysPositionId());
		parcel.setUpdateDate(new Date());
		try {
			Map<String, Object> map = BeanMapUtils.convertBean(parcel);
			List<String> listNumbers = Arrays.asList(parcel.getListNumbers().split(","));
			parcelDeliveryService.addHxParcelDelivery(map, listNumbers);
			return "{\"flag\" : \"success\",\"parcelCode\" : \""+ parcel.getParcelCode() + "\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"flag\" : \"failure\"}";

		}
	}

	/*
	 * 
	 * 修改数据
	 */
	@RequestMapping(value = "/updateView/{parcelCode}", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView updateView(@PathVariable String parcelCode) {
		ModelAndView mav = new ModelAndView("materialmanage/hxParcelDelivery/hxParcelDeliveryUpdate");
		HxParcelDelivery parcel = parcelDeliveryService.selectByPrimaryKey(parcelCode);
		mav.addObject("parcel", parcel);
		return mav;
	}

	//更新邮包
	@RequestMapping(value = "updateParcelDelivery")
	@ResponseBody
	public String updateParcelDelivery(HxParcelDeliveryVO parcel,HttpServletRequest request) {
		try {
			SysUser sysuser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
			parcel.setOrgId(sysuser.getCompanyId());
			parcel.setFounderDate(new Date());
			parcel.setModefieds(sysuser.getUserAccount());
			parcel.setUpdateDate(new Date());
			parcel.setModefiedDate(new Date());
			parcel.setSendUnit(sysuser.getCompanyId());
			parcel.setFittingPositionId(sysuser.getSysPositionId());
			parcel.setState("01");//只有暂存状态才会有修改操作
			List<String> listNumbers = Arrays.asList(request.getParameter("listNumbers").split(","));
			parcelDeliveryService.updateParcelDelivery(parcel, listNumbers);
			return "{\"flag\" : \"success\",\"parcelCode\" : \""+ parcel.getParcelCode() + "\"}";
		} catch (Exception e1) {
			e1.printStackTrace();
			return "{\"flag\" : \"failure\"}";
		}
	}

	@RequestMapping(value = "/SendParcelView")
	@ResponseBody
	public ModelAndView SendParcelView(String parcelCode) {
		ModelAndView mav = new ModelAndView("materialmanage/hxParcelDelivery/hxParcelDeliverySend");
		HxParcelDelivery parcel = parcelDeliveryService.getsendById(parcelCode);
		mav.addObject("parcel", parcel);
		mav.addObject("parcelCode", parcelCode);
		// 02="已发送";03="已收货";01="已录入"
		if ("已发送".equals(parcel.getState()) || "已收货".equals(parcel.getState())) {
			mav.addObject("isEdit", true);
			mav.addObject("isSend", true);
		} else {
			mav.addObject("isEdit", false);
			mav.addObject("isSend", false);
		}
		return mav;
	}

	// 发货
	@RequestMapping(value = "/sendView")
	// 预留
	public ModelAndView sendView(
			@PathVariable(value = "parcelCode") String parcelCode) {
		ModelAndView mav = new ModelAndView(
				"materialmanage/hxParcelDelivery/hxParcelDeliverySendShow");
		HxParcelDelivery parcel = parcelDeliveryService.getsendById(parcelCode);
		mav.addObject("parcel", parcel);
		return mav;
	}

	// 发货
	@RequestMapping(value = "/sendDelivery")
	@ResponseBody
	public String sendDelivery(HttpServletRequest request, String parcelCode){		
		try {
			parcelDeliveryService.updateSendDelivery(parcelCode);
			return "{\"flag\" : \"success\",\"parcelCode\" : \""+ parcelCode + "\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"flag\" : \"failure\"}";
		}
	}

	// 显示效果
	@RequestMapping(value = "/showView/{parcelCode}")
	public ModelAndView showView(
			@PathVariable(value = "parcelCode") String parcelCode) {
		ModelAndView mav = new ModelAndView("materialmanage/hxParcelDelivery/hxParcelDeliveryShow");
		HxParcelDelivery parcel = parcelDeliveryService.getHxParcelDeliveryById(parcelCode);
		mav.addObject("parcel", parcel);
		mav.addObject("parcelCode", parcelCode);
		if ("已发送".equals(parcel.getState()) || "已收货".equals(parcel.getState())) {
			mav.addObject("isEdit", true);
			mav.addObject("isSend", true);
		} else {
			mav.addObject("isEdit", false);
			mav.addObject("isSend", false);
		}
		return mav;
	}

	@RequestMapping(value = "/exportExcel")
	public ModelAndView exportExcel(HxParcelDelivery parcel, String tableField,
			String tableHeader, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		new DecoderUtil<HxParcelDelivery>().decodeURI(parcel);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		List<Map<String, Object>> list = parcelDeliveryService
				.getParcelDeliveryExport(parcel);
		ViewExcel viewExcel = new ViewExcel("邮包信息导出"
				+ DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE),
				tableField, header, list);
		return new ModelAndView(viewExcel);
	}

	@RequestMapping(value = "/getOrgnazationBySuit", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getOrgnazationBySuit(String receiveUnit) throws Exception {
		HxOrganization o = parcelDeliveryService.getOrgnazationBySuit(receiveUnit);
		return JsonUtil.javaObjectToJsonString(o);
	}

	@RequestMapping(value = "/getApplayCodeList", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getApplayCodeList(Page page, HttpServletRequest request)
			throws Exception {
		String receiveUnit = request.getParameter("receiveUnit");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("receiveUnit", receiveUnit);
		page.setParam(map);
		List<Map<String, Object>> list = parcelDeliveryService.getApplayCodeList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);

	}

	@RequestMapping(value = "/getParcelDeliveryDetail/{parcelCode}", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getParcelDeliveryDetail(@PathVariable String parcelCode) throws Exception {
		List<Map<String, Object>> list = parcelDeliveryService.getParcelDeliveryDetail(parcelCode);
		JSONObject object = new JSONObject();
		object.put("total", list.size());
		object.put("rows", JsonHelper.getJsonString4Object(JsonUtil.toGJson(list), "yyyy-MM-dd HH:mm:ss"));
		return object.toString();
	}

	@RequestMapping(value = "/delParcelDelivery")
	@ResponseBody
	public String delParcelDelivery(String parcelCode) throws Exception {
		try {
			parcelDeliveryService.delParcelDelivery(parcelCode);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
}
