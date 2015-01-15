package com.gome.gmhx.controller.basicdata;

import java.net.URLDecoder;
import java.util.Date;
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
import com.gome.common.util.UUIDGenerator;
import com.gome.gmhx.entity.HxFeeMoveMachine;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.service.basicdata.HxMoveChangeCheckoutService;

@Controller
@RequestMapping(value="/standardFee")
public class HxMoveChangeCheckoutController {
	@Resource
	private HxMoveChangeCheckoutService hxCheckoutService;
	
	@RequestMapping(value="/getCombobox/{codeKey}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getCombobox(@PathVariable String codeKey, String value){
		Map<String, String> map = Constrants.CODEMAP.get(codeKey);
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
	
	@RequestMapping(value="/moveChangeCheckoutView")
	public String moveChangeCheckoutView(){
		return "basicData/feeStandard/checkoutStandardList";
	}
	
	@RequestMapping(value="/addView")
	public String addView(){
		return "basicData/feeStandard/checkoutStandardAdd";
	}
	

	@RequestMapping(value="addCheckout")
	@ResponseBody
	public String addCheckout(HttpServletRequest request, HxFeeMoveMachine checkout) {
        try {
 
			checkout.setCreateUsername(((SysUser)request.getSession().getAttribute(Constrants.USER_INFO)).getUserName());
        	checkout.setCreateDate(new Date());//设置创建日期
        	checkout.setFeeID(UUIDGenerator.getUUID());
        	hxCheckoutService.addMoveChangeCheckout(checkout);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	@RequestMapping(value="/updateView/{id}")
	public ModelAndView updateView(@PathVariable(value = "id") String feeID){
		ModelAndView mav = new ModelAndView("basicData/feeStandard/checkoutStandardUpdate");
		HxFeeMoveMachine checkout = hxCheckoutService.getUpdateCheckoutById(feeID);
		mav.addObject("checkout", checkout);
		return mav;
	}
	
	@RequestMapping(value="/updateCheckout")
	@ResponseBody
	public String updateCheckout(HttpServletRequest request, HxFeeMoveMachine checkout){
		try {
			checkout.setModifyUsername(((SysUser)request.getSession().getAttribute(Constrants.USER_INFO)).getUserName());
			checkout.setModifyDate(new Date());//设置创建日期
			hxCheckoutService.updateCheckout(checkout);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@RequestMapping(value="/showView/{id}")
	public ModelAndView showView(@PathVariable(value = "id") String feeID){
		ModelAndView mav = new ModelAndView("basicData/feeStandard/checkoutStandardShow");
		HxFeeMoveMachine checkout = hxCheckoutService.getCheckoutById(feeID);
		mav.addObject("checkout", checkout);
		return mav;
	}
	
	@RequestMapping(value="/deleteMoveCheckout/{idString}")
	@ResponseBody
	public String deleteMoveCheckout(@PathVariable(value = "idString") String idString){
		try {
			hxCheckoutService.deleteMoveCheckout(idString);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@RequestMapping(value="/getHxMoveChangePageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxMoveChangePageList(HttpServletResponse response, Page page, HxFeeMoveMachine checkout) throws Exception{
		Map<String, Object> map = BeanMapUtils.convertBean(checkout);
		page.setParam(map);
		List<Map<String, Object>> list = hxCheckoutService.getHxMoveChangePageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(HxFeeMoveMachine checkout, String tableField, String tableHeader, HttpServletRequest request, HttpServletResponse response) throws Exception{
		new DecoderUtil<HxFeeMoveMachine>().decodeURI(checkout);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		List<Map<String, Object>> list = hxCheckoutService.getCheckoutExport(checkout);
		ViewExcel viewExcel = new ViewExcel("导出实例" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
		return new ModelAndView(viewExcel);
	}

}
