package com.gome.gmhx.controller.basicdata;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.bean.ViewExcel;
import com.gome.common.page.Page;
import com.gome.common.util.DateUtils;
import com.gome.common.util.DecoderUtil;
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.HxPostage;
import com.gome.gmhx.entity.vo.HxPostageVO;
import com.gome.gmhx.service.basicdata.HxPostageService;

@Controller
@RequestMapping(value="/hxPostage")
public class HxPostageController {
	@Resource
	private HxPostageService hxPostageService;
	
	@RequestMapping(value="/hxPostageView")
	public String hxPostageView(){
		return "basicData/hxPostage/hxPostageList";
	}
	                        
	@RequestMapping(value="/getHxPostagePageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxPostagePageList(Page page, HxPostageVO hxPostageVO) throws Exception{
		page.setParam(hxPostageVO);
		List<Map<String, Object>> list = hxPostageService.getHxPostagePageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/addView")
	public String addView(){
		return "basicData/hxPostage/hxPostageAdd";
	}
	
	@RequestMapping(value="/addHxPostage")
	@ResponseBody
	public String addHxPoatage(HxPostage hxPostage, HttpServletRequest request) {
        try {	
        	hxPostageService.addHxPostage(hxPostage);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	@RequestMapping(value="/updateView/{posId}")
	public ModelAndView updateView(@PathVariable(value = "posId") String posId){
		ModelAndView mav = new ModelAndView("basicData/hxPostage/hxPostageUpdate");
		HxPostage hxPostage = hxPostageService.getHxPostageById(posId);
		mav.addObject("hxPostage", hxPostage);
		return mav;
	}
	
	@RequestMapping(value="/updateHxPostage")
	@ResponseBody
	public String updateHxPostage(HxPostage hxPostage, HttpServletRequest request){
		try {
			hxPostageService.updateHxPostage(hxPostage);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(HxPostage hxPostage, String tableField, String tableHeader, HttpServletRequest request, HttpServletResponse response) throws Exception{
		new DecoderUtil<HxPostage>().decodeURI(hxPostage);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		List<Map<String, Object>> list = hxPostageService.getHxPostageExport(hxPostage);
		ViewExcel viewExcel = new ViewExcel("导出实例" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
		return new ModelAndView(viewExcel);
	}
	
	
	@RequestMapping(value="/showView/{posId}")
	public ModelAndView showView(@PathVariable(value = "posId") String posId){
		ModelAndView mav = new ModelAndView("basicData/hxPostage/hxPostageShow");
		HxPostage hxPostage = hxPostageService.getShowById(posId);
		mav.addObject("hxPostage", hxPostage);
		return mav;
	}
	
	@RequestMapping(value="/deleteHxPostage/{posId}")
	@ResponseBody
	public String deleteHxPostage(@PathVariable(value = "posId") String posId){
		try {
			hxPostageService.deleteHxPostage(posId);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@RequestMapping(value="/validatePosNum/{posNum}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String validateModel(@PathVariable String posNum) throws Exception{
		int count = hxPostageService.validatePosNum(posNum);
		return count > 0 ? "success" : "failure";
	}
	
	

}
