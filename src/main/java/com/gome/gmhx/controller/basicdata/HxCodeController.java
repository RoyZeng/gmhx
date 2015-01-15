package com.gome.gmhx.controller.basicdata;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.Constrants;
import com.gome.common.page.Page;
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.HxCode;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.jbpm.CurrentSysUser;
import com.gome.gmhx.service.basicdata.HxCodeService;
import com.gome.gmhx.service.basicdata.HxMaintenanceService;
import com.gome.gmhx.service.common.MachineReviewService;

@Controller
@RequestMapping(value="/hxCode")
public class HxCodeController {
	@Resource
	private HxCodeService hxCodeService;
	@Resource
	private HxMaintenanceService hxMaintenanceService;
	@Resource 
	private MachineReviewService machineReviewService;
	
	
	/*
	 * 获取码表下拉框内容{codeKey}
	 */
	@RequestMapping(value="/getCombobox/{codeKey}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getCombobox(@PathVariable String codeKey, String value){
		if("true".equalsIgnoreCase(value)) value = "1";
		if("false".equalsIgnoreCase(value)) value = "0";
		Map<String, String> map = Constrants.CODEMAP.get(codeKey);
		if(map==null){
			return new JSONArray().toString();
		}
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> iter = set.iterator();
		JSONArray array = new JSONArray();
		JSONObject empty = new JSONObject();
		empty.put("text", "　");
		array.add(empty);
		
		while(iter.hasNext()){
			Entry<String, String> entry = iter.next();
			JSONObject object = new JSONObject();
			object.put("value", entry.getKey());
			object.put("text", entry.getValue());
			if(entry.getKey().equals(value)){
				object.put("selected", true);
			}
			array.add(object);
		}
		return array.toString();
	}
	
	/*
	 * 获取码表下拉框内容{status}
	 */
	@RequestMapping(value="/getStatusCombobox", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getStatusCombobox(String status){
		Map<String, String> map = Constrants.JBPMWORKENTITYSTATUS_MAP;
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> iter = set.iterator();
		JSONArray array = new JSONArray();
		JSONObject empty = new JSONObject();
		empty.put("text", "　");
		array.add(empty);
		
		while(iter.hasNext()){
			Entry<String, String> entry = iter.next();
			JSONObject object = new JSONObject();
			object.put("value", entry.getKey());
			object.put("text", entry.getValue());
			if(entry.getKey().equals(status)){
				object.put("selected", true);
			}
			array.add(object);
		}
		return array.toString();
	}
	
	/*
	 * 获取所有组织机构
	 */
	@RequestMapping(value="/getOrgCombobox", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getOrgCombobox(String value){
		List<Map<String, String>> list = hxCodeService.getOrgCombobox();
		JSONArray array = new JSONArray();
		JSONObject empty = new JSONObject();
		empty.put("text", "　");
		array.add(empty);
		for(Map<String, String> map : list){
			JSONObject object = new JSONObject();
			object.put("value", map.get("id"));
			object.put("text", map.get("name"));
			if(map.get("id").equals(value)){
				object.put("selected", true);
			}
			array.add(object);
		}
		return array.toString();
	}
	
	/*
	 * 获取所有品牌
	 */
	@RequestMapping(value="/getBrandCombobox", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getBrandCombobox(String value){
		List<Map<String, String>> list = hxCodeService.getBrandCombobox();
		JSONArray array = new JSONArray();
		JSONObject empty = new JSONObject();
		empty.put("text", "　");
		array.add(empty);
		for(Map<String, String> map : list){
			JSONObject object = new JSONObject();
			object.put("value", map.get("id"));
			object.put("text", map.get("name"));
			if(map.get("id").equals(value)){
				object.put("selected", true);
			}
			array.add(object);
		}
		return array.toString();
	}
	
	/*
	 * 获取总部组织机构
	 */
	@RequestMapping(value="/getZbOrgCombobox", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getZbOrgCombobox(String value){
		List<Map<String, String>> list = hxCodeService.getZbOrgCombobox();
		JSONArray array = new JSONArray();
		JSONObject empty = new JSONObject();
		empty.put("text", "　");
		array.add(empty);
		for(Map<String, String> map : list){
			JSONObject object = new JSONObject();
			object.put("value", map.get("id"));
			object.put("text", map.get("name"));
			if(map.get("id").equals(value)){
				object.put("selected", true);
			}
			array.add(object);
		}
		return array.toString();
	}
	
	/*
	 * 获取分部组织机构
	 */
	@RequestMapping(value="/getFbOrgCombobox", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getFbOrgCombobox(String value){
		List<Map<String, String>> list = hxCodeService.getFbOrgCombobox();
		JSONArray array = new JSONArray();
		JSONObject empty = new JSONObject();
		empty.put("text", "　");
		array.add(empty);
		for(Map<String, String> map : list){
			JSONObject object = new JSONObject();
			object.put("value", map.get("id"));
			object.put("text", map.get("name"));
			if(map.get("id").equals(value)){
				object.put("selected", true);
			}
			array.add(object);
		}
		return array.toString();
	}
	
	/*
	 * 获取网点组织机构
	 */
	@RequestMapping(value="/getWdOrgCombobox", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getWdOrgCombobox(String value){
		List<Map<String, String>> list = hxCodeService.getWdOrgCombobox();
		JSONArray array = new JSONArray();
		JSONObject empty = new JSONObject();
		empty.put("text", "　");
		array.add(empty);
		for(Map<String, String> map : list){
			JSONObject object = new JSONObject();
			object.put("value", map.get("id"));
			object.put("text", map.get("name"));
			if(map.get("id").equals(value)){
				object.put("selected", true);
			}
			array.add(object);
		}
		return array.toString();
	}
	
	/*
	 * 获取物料操作
	 */
	@RequestMapping(value="/getFittingMenu", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getFittingMenu(HttpServletRequest request){
		SysUser sysUser = (SysUser)request.getSession().getAttribute(Constrants.USER_INFO);
		List<Map<String, String>> list = hxCodeService.getFittingMenu(sysUser.getSysPositionId());
		JSONArray array = new JSONArray();
		
		for(Map<String, String> map : list){
			JSONObject object = new JSONObject();
			object.put("value", map.get("id"));
			object.put("text", map.get("name"));
			array.add(object);
		}
		return array.toString();
	}
	
	/*
	 * 根据AuthId获取物料操作
	 */
	@RequestMapping(value="/getFittingMenuByAuthId", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getFittingMenuByAuthId(String value){
		List<Map<String, String>> list = hxCodeService.getFittingMenuNoPosition();
		JSONArray array = new JSONArray();
		JSONObject empty = new JSONObject();
		empty.put("text", "　");
		array.add(empty);
		for(Map<String, String> map : list){
			JSONObject object = new JSONObject();
			object.put("value", map.get("id"));
			object.put("text", map.get("name"));
			if(map.get("id").equals(value)){
				object.put("selected", true);
			}
			array.add(object);
		}
		return array.toString();		
	}
	
	@RequestMapping(value="/hxCodeView")
	public String hxCodeView(){
		return "basicData/hxCode/hxCodeList";
	}
	
	@RequestMapping(value="/getHxCodePageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxCodePageList(HttpServletResponse response, Page page) throws Exception{
		List<Map<String, Object>> list = hxCodeService.getHxCodePageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/updateView")
	public ModelAndView updateView(HxCode hxCode) throws Exception{
		ModelAndView mav = new ModelAndView("basicData/hxCode/hxCodeUpdate");
		hxCode.setCodeName(URLDecoder.decode(hxCode.getCodeName(), "UTF-8"));
		mav.addObject(hxCode);
		return mav;
	}
	
	@RequestMapping(value="/getHxCodeSettingByCodeId/{codeId}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxCodeSettingByCodeId(@PathVariable String codeId) throws Exception{
		List<Map<String, Object>> list = hxCodeService.getHxCodeSettingByCodeId(codeId);
		return JsonUtil.writeListToDataGrid(list.size(), list);
	}
	
	@RequestMapping(value="/insertSetting", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String insertSetting(HxCode hxCode) throws Exception{
		try{
			hxCodeService.insertSetting(hxCode.getCodeId(),hxCode);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "failure";
		}
	}
	
	/*
	 * 获取岗位下拉框内容{userAccount}
	 */
	@RequestMapping(value="/getPositions", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getPositions(String userAccount){
		List<Map<String, String>> list = hxCodeService.getPositions(userAccount);
		JSONArray array = new JSONArray();
		JSONObject empty = new JSONObject();
		array.add(empty);
		for(Map<String, String> map : list){
			JSONObject object = new JSONObject();
			if(map!=null){
				object.put("value", map.get("id"));
				object.put("text", map.get("name"));
			}
			array.add(object);
		}
		return array.toString();
	}
	
	/*
	 * 获取角色
	 */
	@RequestMapping(value="/getRoleCombobox", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getRoleCombobox(String value){
		List<Map<String, String>> list = hxCodeService.getRoleCombobox();
		JSONArray array = new JSONArray();
		JSONObject empty = new JSONObject();
		empty.put("text", "　");
		array.add(empty);
		for(Map<String, String> map : list){
			JSONObject object = new JSONObject();
			object.put("value", map.get("id"));
			object.put("text", map.get("name"));
			if(map.get("id").equals(value)){
				object.put("selected", true);
			}
			array.add(object);
		}
		return array.toString();
	}
	
	/*
	 * 获取电气公司
	 * ElectricCompany
	 */
	@RequestMapping(value="/getECCombobox", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getECCombobox(String value){
		List<Map<String, String>> list = hxCodeService.getECCombobox();
		JSONArray array = new JSONArray();
		JSONObject empty = new JSONObject();
		empty.put("text", "　");
		array.add(empty);
		for(Map<String, String> map : list){
			JSONObject object = new JSONObject();
			object.put("value", map.get("code"));
			object.put("text", map.get("name"));
			/*if(map.get("value").equals(value)){
				object.put("selected", true);
			}*/
			array.add(object);
		}
		return array.toString();
	}
	
	/*
	 * 获取门店
	 * 
	 */
	@RequestMapping(value="/getStoreCombobox", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getStoreCombobox(String center){
		List<Map<String, String>> list = hxCodeService.getStoreCombobox(center);
		JSONArray array = new JSONArray();
		JSONObject empty = new JSONObject();
		empty.put("text", "　");
		array.add(empty);
		for(Map<String, String> map : list){
			JSONObject object = new JSONObject();
			object.put("value", map.get("code"));
			object.put("text", map.get("name"));
			/*if(map.get("value").equals(value)){
				object.put("selected", true);
			}*/
			array.add(object);
		}
		return array.toString();
	}
	
	/*
	 * 获取网点
	 * Website
	 */
	@RequestMapping(value="/getWebsiteCombobox", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getWebsiteCombobox(String serviceUnit){
		List<Map<String, String>> list = hxCodeService.getWebsiteCombobox(serviceUnit);
		JSONArray array = new JSONArray();
		JSONObject empty = new JSONObject();
		empty.put("text", "　");
		array.add(empty);
		for(Map<String, String> map : list){
			JSONObject object = new JSONObject();
			object.put("value", map.get("code"));
			object.put("text", map.get("name"));
			/*if(map.get("value").equals(value)){
				object.put("selected", true);
			}*/
			array.add(object);
		}
		return array.toString();
	}
	
	/*
	 * 获取售后公司
	 * 
	 */
	@RequestMapping(value="/getAftermarketCombobox", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getAftermarketCombobox(String value){
		List<Map<String, String>> list = hxCodeService.getAftermarketCombobox();
		JSONArray array = new JSONArray();
		JSONObject empty = new JSONObject();
		empty.put("text", "　");
		array.add(empty);
		for(Map<String, String> map : list){
			JSONObject object = new JSONObject();
			object.put("value", map.get("code"));
			object.put("text", map.get("name"));
			/*if(map.get("value").equals(value)){
				object.put("selected", true);
			}*/
			array.add(object);
		}
		return array.toString();
	}
	
	@RequestMapping(value="/getModelCombobox", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getModelCombobox(String category,String type){
		List<Map<String, Object>> list = hxCodeService.getModelCombobox(category,type);
		
		JSONArray array = new JSONArray();
		for(Map<String, Object> map : list){
			JSONObject object = new JSONObject();
			object.put("value", map.get("model"));
			object.put("text", map.get("model"));
			object.put("gomeCode",map.get("gome_code"));
			object.put("brand",map.get("brand"));
			object.put("category",map.get("category"));
			array.add(object);
		}
		return array.toString();
	}
	
	@RequestMapping(value="/getServiceUnit", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getServiceUnit(String wd){
		List<Map<String, Object>> list = hxCodeService.getServiceUnit(wd);
		JSONArray array = new JSONArray();
		for(Map<String, Object> map : list){
			JSONObject object = new JSONObject();
			object.put("value", map.get("code"));
			object.put("text", map.get("name"));
			array.add(object);
		}
		return array.toString();
	}
	
	@RequestMapping(value="/getFittingInfo", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getFittingInfo(String suitModel,String faultCode,String brand,String category){
		List<Map<String, Object>> resultMap = new ArrayList<Map<String, Object>>();
		if(brand!=null&&category!=null){
			suitModel = machineReviewService.mergeMachineCode(category,brand,suitModel);
		}
		if(faultCode.indexOf(",")>-1){
			String[] faults = faultCode.split(",");
			for(String str : faults){
				List<Map<String, Object>> map = hxCodeService.getFittingBySuit(suitModel,str);
				if(map.size()!=0){
					resultMap.addAll(map);
				}
			}
		}else if(faultCode!=null||faultCode!=""){
			resultMap = hxCodeService.getFittingBySuit(suitModel,faultCode);
		}
		JSONArray array = new JSONArray();
		for(Map<String, Object> ma : resultMap){
			JSONObject object = new JSONObject();
			object.put("value", ma.get("fittingCode"));
			object.put("text", ma.get("fittingName"));
			object.put("price", String.valueOf(ma.get("price")));
			array.add(object);
		}
		return array.toString();
	}
	
	@RequestMapping(value="/getCategoryCombobox",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getCategoryCombobox(){
		String position = CurrentSysUser.getCurrentSysUser().getSysPositionId();
		List<Map<String, Object>> list = hxCodeService.getCategoryCombobox(position);
		JSONArray array = new JSONArray();
		for(Map<String, Object> map : list){
			JSONObject object = new JSONObject();
			object.put("value", map.get("code"));
			object.put("text", map.get("value"));
			array.add(object);
		}
		return array.toString();
	}
	
	
	@RequestMapping(value="/getFaultCodeCombobox",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getFaultCodeCombobox(){
		List<Map> codes = hxMaintenanceService.getAllMaintenance();
        JSONArray array = new JSONArray();
        for (Map<String, String> map : codes) {
            JSONObject object = new JSONObject();
             object.put("value", map.get("fault_code"));
             object.put("text", map.get("fault_name"));
             array.add(object);
        }
		return array.toString();
	}
	
	@RequestMapping(value="/getRegionCombobox",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getRegionCombobox(HttpServletRequest request, String q){
		if(StringUtils.trimToNull(q)==null) return new JSONArray().toString();
		List<Map> codes = hxCodeService.getRegionCombobox(StringUtils.trimToNull(q));
		JSONArray array = new JSONArray();
		for(Map<String,String> map : codes){
			 JSONObject object = new JSONObject();
             object.put("value", map.get("code"));
             object.put("text", map.get("text"));
             object.put("paren", map.get("parent"));
             if(map.get("text").equals(q)){
            	 object.put("selected", true);
             }
             array.add(object);
             if(array.size()==10){
 				break;
 			}
		}
		return array.toString();
	}
}
