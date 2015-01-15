package com.gome.gmhx.controller.basicdata;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.gome.gmhx.entity.HxProductDetail;
import com.gome.gmhx.service.basicdata.HxProductDetailService;

@Controller
@RequestMapping(value="/hxProductDetail")
public class HxProductDetailController {
	@Resource
	private HxProductDetailService hxProductDetailService;
	
	@RequestMapping(value="/hxProductDetailView")
	public String hxCodeView(){
		return "basicData/hxProductDetail/hxProductDetailList";
	}
	
	@RequestMapping(value="/addView")
	public String addView(){
		return "basicData/hxProductDetail/hxProductDetailAdd";
	}
	
	@RequestMapping(value="/getHxProductDetailPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxProductDetailPageList(Page page, HxProductDetail hxProductDetail,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date ksrq, @DateTimeFormat(pattern="yyyy-MM-dd")Date jsrq, String brand) throws Exception{
		Map<String, Object> map = BeanMapUtils.convertBean(hxProductDetail);
		map.put("ksrq", ksrq);
		map.put("jsrq", jsrq);
		map.put("brand", brand);
		page.setParam(map);
		List<Map<String, Object>> list = hxProductDetailService.getHxProductDetailPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/updateView/{id}")
	public ModelAndView updateView(@PathVariable String id) throws Exception{
		ModelAndView mav = new ModelAndView("basicData/hxProductDetail/hxProductDetailUpdate");
		HxProductDetail hxProductDetail = hxProductDetailService.getHxProductDetailById(id);
		mav.addObject(hxProductDetail);
		return mav;
	}
	
	@RequestMapping(value="/addHxProductDetail")
	@ResponseBody
	public String addHxProductDetail(HxProductDetail hxProductDetail) throws Exception{
		try {
			hxProductDetail.setId(UUIDGenerator.getUUID());
			hxProductDetailService.addHxProductDetail(hxProductDetail);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
	@RequestMapping(value="/updateHxProductDetail")
	@ResponseBody
	public String updateHxProductDetail(HxProductDetail hxProductDetail) throws Exception{
		try {
			hxProductDetail.setUpdateTime(new Date());
			hxProductDetailService.updateHxProductDetail(hxProductDetail);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"flag\" : \"failure\"}";
        }
	}
	
	@RequestMapping(value="/viewHxProductDetail/{id}")
	public ModelAndView viewHxProductDetail(@PathVariable String id) throws Exception{
		ModelAndView mav = new ModelAndView("basicData/hxProductDetail/hxProductDetailView");
		HxProductDetail hxProductDetail = hxProductDetailService.getHxProductDetailById(id);
		mav.addObject(hxProductDetail);
		return mav;
	}
}
