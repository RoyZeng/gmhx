package com.gome.gmhx.controller.basicdata;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.page.Page;
import com.gome.common.util.BeanMapUtils;
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.HxFitting;
import com.gome.gmhx.entity.vo.HxFittingVO;
import com.gome.gmhx.service.basicdata.HxFittingService;

@Controller
@RequestMapping(value="/hxFitting")
public class HxFittingController {
	@Resource
	private HxFittingService hxFittingService;
	
	@RequestMapping(value="/hxFittingView")
	public String hxCodeView(){
		return "basicData/hxFitting/hxFittingList";
	}
	
	@RequestMapping(value="/addView")
	public String addView(){
		return "basicData/hxFitting/hxFittingAdd";
	}
	
	@RequestMapping(value="/getHxFittingPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxFittingPageList(HttpServletResponse response, Page page, HxFitting hxFitting,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date ksrq, @DateTimeFormat(pattern="yyyy-MM-dd")Date jsrq,
			 String suitModel) throws Exception{
		Map<String, Object> map = BeanMapUtils.convertBean(hxFitting);
		map.put("ksrq", ksrq);
		map.put("jsrq", jsrq);
		map.put("suitModel", suitModel);
		page.setParam(map);
		List<Map<String, Object>> list = hxFittingService.getHxFittingPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/updateView/{fittingCode}")
	public ModelAndView updateView(@PathVariable String fittingCode) throws Exception{
		ModelAndView mav = new ModelAndView("basicData/hxFitting/hxFittingUpdate");
		HxFitting hxFitting = hxFittingService.getHxFittingByFittingCode(fittingCode);
		mav.addObject(hxFitting);
		return mav;
	}
	
	@RequestMapping(value="/addHxFitting")
	@ResponseBody
	public String addHxFitting(@RequestBody HxFittingVO hxFittingVO) throws Exception{
		try {
			hxFittingService.addHxFitting(hxFittingVO);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
	@RequestMapping(value="/updateHxFitting")
	@ResponseBody
	public String updateHxFitting(@RequestBody HxFittingVO hxFittingVO) throws Exception{
		try {
			hxFittingService.updateHxFitting(hxFittingVO);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
	@RequestMapping(value="/viewHxFitting/{fittingCode}/{pageMarkup}")
	public ModelAndView viewHxFitting(@PathVariable String fittingCode, @PathVariable String pageMarkup) throws Exception{
		ModelAndView mav = new ModelAndView("basicData/hxFitting/hxFittingView");
		HxFitting hxFitting = hxFittingService.getHxFittingByFittingCode(fittingCode);
		mav.addObject(hxFitting);
		mav.addObject("pageMarkup", pageMarkup);
		return mav;
	}
	
	@RequestMapping(value="/getHxFittingModel/{fittingCode}")
	@ResponseBody
	public String getHxFittingModel(@PathVariable String fittingCode) throws Exception{
		List<Map<String, Object>> list = hxFittingService.getHxFittingModelByFittingCode(fittingCode);
		return JsonUtil.writeListToDataGrid(list.size(), list);
	}
	
	@RequestMapping(value="/getHxFittingProvider/{fittingCode}")
	@ResponseBody
	public String getHxFittingProvider(@PathVariable String fittingCode) throws Exception{
		List<Map<String, Object>> list = hxFittingService.getHxFittingProviderByFittingCode(fittingCode);
		return JsonUtil.writeListToDataGrid(list.size(), list);
	}
	
	@RequestMapping(value="/getHxFittingFaultCode/{fittingCode}")
	@ResponseBody
	public String getHxFittingFaultCode(@PathVariable String fittingCode) throws Exception{
		List<Map<String, Object>> list = hxFittingService.getHxFittingFaultCodeByFittingCode(fittingCode);
		return JsonUtil.writeListToDataGrid(list.size(), list);
	}
}
