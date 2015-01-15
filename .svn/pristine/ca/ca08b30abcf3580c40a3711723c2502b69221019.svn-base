package com.gome.gmhx.controller.materialmanage;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.Constrants;
import com.gome.common.page.Page;
import com.gome.common.util.BeanMapUtils;
import com.gome.common.util.DateUtils;
import com.gome.common.util.JsonHelper;
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.HxFitting;
import com.gome.gmhx.entity.HxMaterialDetail;
import com.gome.gmhx.entity.HxMaterialManage;
import com.gome.gmhx.entity.HxUser;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.entity.vo.HxMaterialVO;
import com.gome.gmhx.service.basicdata.HxFittingService;
import com.gome.gmhx.service.materialmanage.HxMaterialService;
import com.gome.gmhx.service.sysconfig.HxUserService;

@Controller
@RequestMapping(value="/hxMaterial")
public class HxMaterialController {
	
	@Resource
	private HxMaterialService hxMaterialService;
	
	@Resource
	private HxFittingService hxFittingService;
	
	@Resource
	private HxUserService hxUserService;
	
	@RequestMapping(value="/hxMaterialView")
	public String hxMaterialView(){
		return "materialmanage/hxMaterial/hxMaterialList";
	}
	
	@RequestMapping(value="/getHxMaterialDetailView/{listNumber}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public ModelAndView getHxMaterialDetailView(@PathVariable String listNumber) throws Exception{
		ModelAndView mav = new ModelAndView("workflow/material/materialOut");
		HxMaterialManage manage = hxMaterialService.getHxMaterialManage(listNumber);
		String applicant=manage.getApplicant();
		if(applicant!=null && applicant.trim().contains("_")){
			String[] array=applicant.split("_");
			HxUser applicantUser = hxUserService.queryUserByUserLoginName(array[array.length-1]);
			manage.setApplicant(applicantUser.getUserName());
		}
		mav.addObject("m", manage);
		mav.addObject("title", Constrants.FITTING_TITLE_MAP.get(manage.getType()));
		mav.addObject("listNumber", listNumber);
		return mav;
	}
	
	@RequestMapping(value="/getHxMaterialPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxMaterialPageList(HttpServletRequest request, Page page, HxMaterialManage hxMaterialManage) throws Exception{
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constrants.USER_INFO);
		Map<String, Object> map = BeanMapUtils.convertBean(hxMaterialManage);
		map.put("ksrq", request.getParameter("ksrq"));
		map.put("jsrq", request.getParameter("jsrq"));
		map.put("fittingPositionId", sysUser.getSysPositionId());
		map.put("fittingOrgId", sysUser.getCompanyId());
		map.put("userAccount", sysUser.getUserAccount());
		page.setParam(map);
		List<Map<String, Object>> list = hxMaterialService.getHxMaterialPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/hxMaterialAdd")
	public ModelAndView hxMaterialAdd(String type){
		ModelAndView mav = new ModelAndView("materialmanage/hxMaterial/hxMaterialAdd");
		mav.addObject("date", DateUtils.formatDateTime(new Date(), DateUtils.LONG_DATE_FORMAT));
		mav.addObject("type", type);
		mav.addObject("title", Constrants.FITTING_TITLE_MAP.get(type));
		return mav;
	}
	
	@RequestMapping(value="/addHxMaterial")
	@ResponseBody
	public String addHxMaterial(@RequestBody HxMaterialVO hxMaterialVO, HttpServletRequest request){
		SysUser sysuser = (SysUser)request.getSession().getAttribute(Constrants.USER_INFO);
		HxMaterialManage manage = hxMaterialVO.getManage();
		String createPerson = sysuser.getUserAccount();
		Date date = Calendar.getInstance().getTime();
		String type = hxMaterialVO.getAuthId();
		
		manage.setType(type);
		manage.setFittingOrgId(sysuser.getCompanyId());
		manage.setFittingPositionId(sysuser.getSysPositionId());
		manage.setStatus("S1");
		manage.setCreatePerson(createPerson);
		manage.setCreateTime(date);
		manage.setUpdatePerson(createPerson);
		manage.setUpdateTime(date);
		manage.setListNumber(getListNumber(sysuser.getCompanyId()));
		
		if(type.equals("zb-n-cg")){
			manage.setStatus("S0");
			manage.setReceiveCompany(sysuser.getCompanyId());
		}
		
		if(type.startsWith("zb-") && !type.equals("zb-n-db")){
			if(type.indexOf("-rk-") > 0) manage.setReceiveCompany("wlzb");
			if(type.indexOf("-ck-") > 0) manage.setSendCompany("wlzb");
			
		}
		if(type.equals("fb-n-db")){
			//从配件库发货
			manage.setReceiveCompany(sysuser.getCompanyId());
		}
		if(type.equals("fb-blp-fh") || type.equals("fb-ll-bs")
			|| type.equals("fb-n-th") || type.equals("fb-o-fh")
			|| type.equals("wd-blp-fh")
			 || type.equals("wd-yjjh-sq")){
			manage.setSendCompany(hxMaterialService.getFatherFittingOrgId(sysuser.getCompanyId()));
			manage.setReceiveCompany(sysuser.getCompanyId());
		}
		if(type.equals("wd-n-th") || type.equals("wd-o-fh") || type.equals("wd-ll-bl")){
			manage.setSendCompany(sysuser.getCompanyId());
			manage.setReceiveCompany(hxMaterialService.getWebFittingOrgId(sysuser.getCompanyId()));
		}
		if(type.equals("fb-n-cg") || type.equals("fb-n-sq")){
			manage.setSendCompany(hxMaterialService.getFatherFittingOrgId(sysuser.getCompanyId()));
			manage.setReceiveCompany(sysuser.getCompanyId());
		}
		if(type.equals("wd-n-cg") || type.equals("wd-n-sq")){
			manage.setSendCompany(hxMaterialService.getWebFittingOrgId(sysuser.getCompanyId()));
			manage.setReceiveCompany(sysuser.getCompanyId());
		}
		if(type.equals("fb-n-ck-xs") || type.equals("fb-o-bf")
				|| type.equals("wd-ll-bs") || type.equals("wd-bw-xs")){
				manage.setSendCompany(sysuser.getCompanyId());
		}
		if(type.equals("fb-kc-zy")){
			manage.setSendCompany(sysuser.getCompanyId());
			manage.setReceiveCompany(sysuser.getCompanyId());
		}
		try{
			for (HxMaterialDetail detail : hxMaterialVO.getDetail()) {
				//给配件赋值price
				detail.setPrice(Float.valueOf((String)getFittingInfo2(detail.getFittingCode(), sysuser.getCompanyId(), type, null).get("price")));
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map = BeanMapUtils.convertBean(hxMaterialVO);
			hxMaterialService.addHxMaterial(map);
			return "{\"flag\" : \"success\", \"listNumber\" : \"" + hxMaterialVO.getManage().getListNumber() + "\"}";
		}catch(Exception e){
			e.printStackTrace();
			return "{\"flag\" : \"failure\"}";
		}
	}
	
	@RequestMapping(value="/viewHxMaterial")
	@ResponseBody
	public ModelAndView viewHxMaterial(String listNumber, String type){
		ModelAndView mav = new ModelAndView("materialmanage/hxMaterial/hxMaterialView");
		HxMaterialManage manage = hxMaterialService.getHxMaterialManageShow(listNumber);
		mav.addObject("type", type);
		mav.addObject("title", Constrants.FITTING_TITLE_MAP.get(type));
		mav.addObject("m", manage);
		if("S1".equals(manage.getStatus())){
			mav.addObject("isEdit", true);
		}else{
			mav.addObject("isEdit", false);
		}
		if(type.indexOf("-rk-") > 0 || type.indexOf("-ck-") > 0){
			mav.addObject("isSend", false);
		}else{
			mav.addObject("isSend", true);
		}
		return mav;
	}
	
	@RequestMapping(value="/updateHxMaterial")
	@ResponseBody
	public ModelAndView updateHxMaterial(String listNumber, String type){
		ModelAndView mav = new ModelAndView("materialmanage/hxMaterial/hxMaterialUpdate");
		HxMaterialManage manage = hxMaterialService.getHxMaterialManage(listNumber);
		mav.addObject("type", type);
		mav.addObject("title", Constrants.FITTING_TITLE_MAP.get(type));
		mav.addObject("m", manage);
		return mav;
	}
	
	@RequestMapping(value="/updateHxMaterialDetail")
	@ResponseBody
	public String updateHxMaterialDetail(HttpServletRequest request){
		String listNumber = (String)request.getParameter("listNumber");
		String []codes =(String [])request.getParameter("codes").split(",");
		String []counts =(String [])request.getParameter("counts").split(",");
//		String []comments =(String [])request.getParameter("comments").split(",");
		ArrayList<HxMaterialDetail> list = new ArrayList<HxMaterialDetail>(counts.length);
		for(int i=0; i<codes.length; i++){
			HxMaterialDetail detail = new HxMaterialDetail();
			detail.setListNumber(listNumber);
			detail.setFittingCode(codes[i]);
			//detail.setApplyCount(Integer.valueOf(counts[i]));
			if (counts[i] != null && !counts[i].equals(""))
				detail.setAuditCount(Integer.valueOf(counts[i]));
//			detail.setComment(comments[i]);
			list.add(detail);
		}
		try {
			this.hxMaterialService.updateHxMaterialDetails(list);
			return "{\"flag\" : \"success\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"flag\" : \"failure\"}";
		}
	}
	//出库
	@RequestMapping("reduceStock")
	@ResponseBody
	public String reduceStock(HttpServletRequest request) throws UnsupportedEncodingException{
		String listNumber = (String)request.getParameter("listNumber");
		try {
			this.hxMaterialService.updateReduceStock(listNumber);
			return "{\"flag\" : \"success\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"flag\" : \"failure\",\"info\" : \""+URLEncoder.encode(e.getMessage(),"UTF-8")+"\"}";
		}
	}
	
	//出库
	@RequestMapping("updateMaterialLostReduceStock")
	@ResponseBody
	public String updateMaterialLostReduceStock(HttpServletRequest request) throws UnsupportedEncodingException{
		String listNumber = (String)request.getParameter("listNumber");
		try {
			this.hxMaterialService.updateMaterialLostReduceStock(listNumber);
			return "{\"flag\" : \"success\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"flag\" : \"failure\",\"info\" : \""+URLEncoder.encode(e.getMessage(),"UTF-8")+"\"}";
		}
	}
	//转移库存
	@RequestMapping("updateMaterialMove")
	@ResponseBody
	public String updateMaterialMove(HttpServletRequest request) throws UnsupportedEncodingException{
		String listNumber = (String)request.getParameter("listNumber");
		try {
			this.hxMaterialService.updateMaterialMove(listNumber);
			return "{\"flag\" : \"success\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"flag\" : \"failure\",\"info\" : \""+URLEncoder.encode(e.getMessage(),"UTF-8")+"\"}";
		}
	}
	
	//发货
	@RequestMapping("wdnthDelivery")
	@ResponseBody
	public String wdnthDelivery(HttpServletRequest request) throws UnsupportedEncodingException{
		String listNumber = (String)request.getParameter("listNumber");
		try {
			this.hxMaterialService.updateDelivery(listNumber);
			return "{\"flag\" : \"success\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"flag\" : \"failure\",\"info\" : \""+URLEncoder.encode(e.getMessage(),"UTF-8")+"\"}";
		}
	}
	
	//收货
	@RequestMapping("wdnthReceive")
	@ResponseBody
	public String wdnthReceive(HttpServletRequest request){
		String listNumber = (String)request.getParameter("listNumber");
		try {
			this.hxMaterialService.updateReceive(listNumber);
			return "{\"flag\" : \"success\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"flag\" : \"failure\"}";
		}
	}
	
	//检测
	@RequestMapping("wdnthCheck")
	@ResponseBody
	public String wdnthCheck(HttpServletRequest request){
		String listNumber = (String)request.getParameter("listNumber");
		try {
			this.hxMaterialService.updateCheck(listNumber);
			return "{\"flag\" : \"success\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"flag\" : \"failure\"}";
		}
	}
	//入库
	@RequestMapping("wdnthIn")
	@ResponseBody
	public String wdnthIn(HttpServletRequest request) throws UnsupportedEncodingException{
		String listNumber = (String)request.getParameter("listNumber");
		try {
			this.hxMaterialService.updateIn(listNumber);
			return "{\"flag\" : \"success\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"flag\" : \"failure\",\"info\" : \""+URLEncoder.encode(e.getMessage(),"UTF-8")+"\"}";
		}
	}
	@RequestMapping(value="/saveHxMaterial")
	@ResponseBody
	public String saveHxMaterial(@RequestBody HxMaterialVO hxMaterialVO, HttpServletRequest request){
		SysUser sysuser = (SysUser)request.getSession().getAttribute(Constrants.USER_INFO);
		HxMaterialManage manage = hxMaterialVO.getManage();
		Date date = Calendar.getInstance().getTime();
		String type = hxMaterialVO.getAuthId();
		
		manage.setType(type);
		manage.setUpdatePerson(sysuser.getUserAccount());
		manage.setUpdateTime(date);
		for (HxMaterialDetail detail : hxMaterialVO.getDetail()) {
			//给配件赋值price
			detail.setPrice(Float.valueOf((String)getFittingInfo2(detail.getFittingCode(), sysuser.getCompanyId(), type, null).get("price")));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			map = BeanMapUtils.convertBean(hxMaterialVO);
			hxMaterialService.updateHxMaterial(map);
			return "{\"flag\" : \"success\", \"listNumber\" : \"" + manage.getListNumber() + "\"}";
		}catch(Exception e){
			e.printStackTrace();
			return "{\"flag\" : \"failure\"}";
		}
	}
	
	@RequestMapping(value="/delHxMaterial")
	@ResponseBody
	public String delHxMaterial(String listNumber) throws Exception{
		try{
			hxMaterialService.delHxMaterial(listNumber);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "failure";
		}
	}
	
	@RequestMapping(value="/getHxMaterialDetails/{listNumber}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxMaterialDetails(@PathVariable String listNumber) throws Exception{
		List<Map<String, Object>> list = hxMaterialService.getHxMaterialDetails(listNumber);
		JSONObject object = new JSONObject();
    	object.put("total", list.size());
    	object.put("rows", JsonHelper.getJsonString4Object(JsonUtil.toGJson(list), "yyyy-MM-dd HH:mm:ss"));
    	double totalPrice = 0;
    	for(Map<String, Object> map : list){
    		totalPrice += (Float)map.get("price") * (map.get("auditCount")==null ? (Integer)map.get("applyCount") : (Integer)map.get("auditCount")) ;
    	}
    	object.put("footer", "[{\"spec\":\"总计（元）：\",\"price\":" + totalPrice + "}]");
		return object.toString();
	}
	
	@RequestMapping(value="/getHxMaterialDetail3/{listNumber}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxMaterialDetail3(@PathVariable String listNumber) throws Exception{
		List<Map<String, Object>> list = hxMaterialService.getHxMaterialDetail3(listNumber);
		JSONObject object = new JSONObject();
    	object.put("total", list.size());
    	object.put("rows", JsonHelper.getJsonString4Object(JsonUtil.toGJson(list), "yyyy-MM-dd HH:mm:ss"));
		return object.toString();
	}
	
	@RequestMapping(value="/getHxMaterialDetail/{listNumber}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxMaterialDetail(@PathVariable String listNumber) throws Exception{
		
		List<Map<String, Object>> list = hxMaterialService.getHxMaterialDetail(listNumber);
		JSONObject object = new JSONObject();
    	object.put("total", list.size());
    	object.put("rows", JsonHelper.getJsonString4Object(JsonUtil.toGJson(list), "yyyy-MM-dd HH:mm:ss"));
    	double totalPrice = 0;
    	for(Map<String, Object> map : list){
    		totalPrice += (Float)map.get("price") * (map.get("audit_count")==null ? (Integer)map.get("apply_count") : (Integer)map.get("audit_count")) ;
    	}
    	object.put("footer", "[{\"spec\":\"总计（元）：\",\"price\":" + totalPrice + "}]");
		return object.toString();
	}
	
	@RequestMapping(value="/getHxMaterialDetailForUpdate/{listNumber}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxMaterialDetailForUpdate(@PathVariable String listNumber, String type) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		HxMaterialManage manage = hxMaterialService.getHxMaterialManage(listNumber);
		map.put("companyId", manage.getFittingOrgId());
		if(type.indexOf("-o-") > 0){
			map.put("isNew", 0);
		}else{
			map.put("isNew", 1);
		}
		map.put("listNumber", listNumber);
		List<Map<String, Object>> list = hxMaterialService.getHxMaterialDetailForUpdate(map);
		return JsonUtil.writeListToDataGrid(list.size(), list);
	}
	
	@RequestMapping(value="/hxMaterialSend")
	@ResponseBody
	public String hxMaterialSend(String listNumber,String type) throws UnsupportedEncodingException{//报批
		Map<String, String> map = new HashMap<String, String>();
		map.put("listNumber", listNumber);
		try{
			hxMaterialService.hxMaterialSend(listNumber,type);
			return "{\"flag\" : \"success\"}";
		}catch(Exception e){
			e.printStackTrace();
			return "{\"flag\" : \"failure\",\"info\" : \""+URLEncoder.encode(e.getMessage(),"UTF-8")+"\"}";
		}
	}
	
	@RequestMapping(value="/hxMaterialSure")
	@ResponseBody
	public String hxMaterialSure(@RequestBody HxMaterialVO hxMaterialVO){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			map = BeanMapUtils.convertBean(hxMaterialVO);
			hxMaterialService.updateCurrentStock(map);
			return "{\"flag\" : \"success\", \"listNumber\" : \"" + hxMaterialVO.getManage().getListNumber() + "\"}";
		}catch(Exception e){
			e.printStackTrace();
			return "{\"flag\" : \"failure\"}";
		}
	}
	
	/*
	 * 获取手工单号
	 */
	private synchronized String getListNumber(String companyId){
		StringBuilder sb = new StringBuilder("SQ-");
		sb.append(companyId);
		sb.append("-");
		String date = DateUtils.formatDateTime(new Date(), DateUtils.EIGHT_STYLE_DATE_FORMAT);
		sb.append(date.substring(2, 8));
		sb.append(hxMaterialService.getListNumber(companyId));
		return sb.toString();
	}
	
	@RequestMapping(value="/getFittingInfo/{fittingId}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getFittingInfo(@PathVariable String fittingId, HttpServletRequest request, String authId){
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constrants.USER_INFO);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fittingId", fittingId);
		map.put("orgId", sysUser.getCompanyId());
		String move = request.getParameter("move");//分部库存转移
		return getFittingInfo2(fittingId, sysUser.getCompanyId(), authId, move).toString();
	}
	
	private JSONObject getFittingInfo2(String fittingId, String orgId, String authId, String move){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fittingId", fittingId);
		map.put("orgId", orgId);
		if(authId.indexOf("-o-") > 0){
			map.put("isNew", 0);
		}else if(authId.indexOf("-n-") >0){
			map.put("isNew", 1);
		}else if(authId.indexOf("fb-kc-zy") > -1){
			//残品转良品应显示旧料库存，良品转残品应显示新料库存
			if(move != null && move.equals("1")){
				map.put("isNew", 0);
			}else if(move != null && move.equals("0")){
				map.put("isNew", 1);
			}
		}else{
			map.put("isNew", 1);
		}
		JSONObject object = new JSONObject();
		try{
			Map<String, Object> resultMap = hxMaterialService.getFittingInfo(map);
			if(resultMap == null){
				HxFitting hxFitting = hxFittingService.getHxFittingByFittingCode(fittingId);
				object.put("stock", 0);
				object.put("spec", hxFitting.getSpec());
				object.put("fittingName", hxFitting.getFittingName());
				if(authId.startsWith("wd-")){
					object.put("price", String.valueOf(hxFitting.getNetworkPrice()));
				}else if(authId.startsWith("zb-") || authId.startsWith("fb-")){
					object.put("price", String.valueOf(hxFitting.getBranchPrice()));
				}else{
					object.put("price", 0);
				}
			}else{
				object.put("stock", resultMap.get("stock"));
				object.put("spec", resultMap.get("spec"));
				object.put("fittingName", resultMap.get("fittingName"));
				object.put("price", String.valueOf(resultMap.get("price")));
			}
			object.put("flag", "success");
		}catch(Exception e){
			object.put("flag", "failure");
			object.put("info", e.getMessage());
		}
		return object;
	}
	
	
	@RequestMapping(value="/getFittingBySuit", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getFittingBySuit(String suitModel,String faultCode) throws UnsupportedEncodingException{
		suitModel = java.net.URLDecoder.decode(suitModel, "UTF-8");
		List<String> lists = new ArrayList<String>();
		if(faultCode!=null&&faultCode!=""){
			if(faultCode.indexOf(",")>-1){
				String[] faults = faultCode.split(",");
				for(String str : faults){
					List<String> list = hxMaterialService.getFittingBySuit(suitModel,str);
					if(list.size()!=0){
						lists.addAll(list);
					}
				}
			}else if(faultCode!=null||faultCode!=""){
				lists = hxMaterialService.getFittingBySuit(suitModel,faultCode);
			}
		}else{
			lists = hxMaterialService.getFittingBySuit(suitModel,"");
		}
		JSONArray array = new JSONArray();
		for(String str : lists){
			JSONObject object = new JSONObject();
			object.put("value", str);
			object.put("text", str);
			array.add(object);
		}
		return array.toString();
	}
	
	@RequestMapping(value="/getStock/{listNumber}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxMaterialStock(HttpServletRequest request, @PathVariable String listNumber) throws Exception{
		List<Map<String, Object>> list = hxMaterialService.getHxMaterialStock(listNumber);
		return JsonUtil.writeListToDataGrid(list.size(), list);
	}
	
	@RequestMapping(value="/getListStockPrice", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getListStockPrice(String listNumber, String fittingCode, String authId){
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("listNumber", listNumber);
		map.put("fittingCode", fittingCode);
		Long price = hxMaterialService.getListStockPrice(map);
		if(price == null){
			HxFitting hxFitting = hxFittingService.getHxFittingByFittingCode(fittingCode);
			if(authId.startsWith("wd-")){
				return String.valueOf(hxFitting.getNetworkPrice());
			}else if(authId.startsWith("zb-") || authId.startsWith("fb-")){
				return String.valueOf(hxFitting.getBranchPrice());
			}else{
				return "0";
			}
		}
		return String.valueOf(price);
	}
}
