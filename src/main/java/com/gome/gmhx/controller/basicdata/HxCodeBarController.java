package com.gome.gmhx.controller.basicdata;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.page.Page;
import com.gome.common.util.BeanMapUtils;
import com.gome.common.util.JsonUtil;
import com.gome.common.util.UUIDGenerator;
import com.gome.gmhx.entity.HxCodeBar;
import com.gome.gmhx.service.basicdata.HxCodeBarService;

@Controller
@RequestMapping(value="/hxCodeBar")
public class HxCodeBarController {
	
	@Resource
	private HxCodeBarService hxCodeBarService;
	
	@RequestMapping(value="/hxCodeBarView")
	public String hxCodeView(){
		return "basicData/hxCodeBar/hxCodeBarList";
	}
	
	@RequestMapping(value="/addView")
	public String addView(){
		return "basicData/hxCodeBar/hxCodeBarAdd";
	}
	
	@RequestMapping(value="/viewHxCodeBar/{id}")
	public ModelAndView viewHxProductDetail(@PathVariable String id) throws Exception{
		ModelAndView mav = new ModelAndView("basicData/hxCodeBar/hxCodeBarView");
		HxCodeBar hxCodeBar = hxCodeBarService.getHxCodeBarById(id);
		mav.addObject(hxCodeBar);
		return mav;
	}
	
	@RequestMapping(value="/updateView/{id}")
	public ModelAndView updateView(@PathVariable String id) throws Exception{
		ModelAndView mav = new ModelAndView("basicData/hxCodeBar/hxCodeBarUpdate");
		HxCodeBar hxCodeBar = hxCodeBarService.getHxCodeBarById(id);
		mav.addObject(hxCodeBar);
		return mav;
	}
	
	@RequestMapping(value="/updateHxCodeBar")
	@ResponseBody
	public String updateHxCodeBar(HxCodeBar hxCodeBar) throws Exception{
		try {
			hxCodeBarService.updateHxCodeBar(hxCodeBar);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
	}
	
	@RequestMapping(value="/getHxCodeBarPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxCodeBarPageList(HttpServletResponse response, Page page, HxCodeBar hxCodeBar,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date ksrq, @DateTimeFormat(pattern="yyyy-MM-dd")Date jsrq)
			throws Exception{
		Map<String, Object> map = BeanMapUtils.convertBean(hxCodeBar);
		map.put("ksrq", ksrq);
		map.put("jsrq", jsrq);
		page.setParam(map);
		List<Map<String, Object>> list = hxCodeBarService.getHxCodeBarPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/validateInnerCode1", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String validateInnerCode1(String code)
			throws Exception{
		int count = hxCodeBarService.validateInnerCode1(code);
		return count > 0 ? "success" : "failure";
	}
	
	@RequestMapping(value="/validateInnerCode2", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String validateInnerCode2(String code)
			throws Exception{
		int count = hxCodeBarService.validateInnerCode2(code);
		return count > 0 ? "success" : "failure";
	}
	
	@RequestMapping(value="/validateOuterCode", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String validateOuterCode(String code) throws Exception{
		int count = hxCodeBarService.validateOuterCode(code);
		return count > 0 ? "success" : "failure";
	}
	
	@RequestMapping(value="/addHxCodeBar")
	@ResponseBody
	public String addHxCodeBar(HxCodeBar hxCodeBar) throws Exception{
		try {
			hxCodeBar.setId(UUIDGenerator.getUUID());
			hxCodeBarService.addHxCodeBar(hxCodeBar);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
	@RequestMapping(value="/validateModel/{model}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String validateModel(@PathVariable String model) throws Exception{
		int count = hxCodeBarService.validateModel(model);
		return count > 0 ? "success" : "failure";
	}
}
