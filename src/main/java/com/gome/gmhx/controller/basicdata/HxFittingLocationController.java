package com.gome.gmhx.controller.basicdata;

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

import com.gome.common.bean.ViewExcel;
import com.gome.common.page.Page;
import com.gome.common.util.BeanMapUtils;
import com.gome.common.util.DateUtils;
import com.gome.common.util.DecoderUtil;
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.HxFittingLocation;
import com.gome.gmhx.service.basicdata.HxFittingLocationService;

@Controller
@RequestMapping(value="/hxFittingLocation")
public class HxFittingLocationController {
	@Resource
	private HxFittingLocationService hxFittingLocationService;
	
	@RequestMapping(value="/hxFittingLocationView")
	public String hxFittingLocationView(){
		return "basicData/hxFittingLocation/hxFittingLocationList";
	}
	                        
	@RequestMapping(value="/getHxFittingLocationPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxFittingLocationPageList(Page page, HxFittingLocation hxFittingLocation,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date modifieDate_sta,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date modifieDate_end) throws Exception{
		Map <String , Object> map =BeanMapUtils.convertBean(hxFittingLocation);
		map.put("modifieDate_sta", modifieDate_sta);
		map.put("modifieDate_sta", modifieDate_sta);
		page.setParam(map);
		List<Map<String, Object>> list = hxFittingLocationService.getHxFittingLocationPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/addView")
	public String addView(){
		return "basicData/hxFittingLocation/hxFittingLocationAdd";
	}
	
	@RequestMapping(value="/addHxFittingLocation")
	@ResponseBody
	public String addHxFittingLocation(HxFittingLocation hxFittingLocation, HttpServletRequest request) {
        try {	
        	hxFittingLocationService.addHxFittingLocation(hxFittingLocation);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	@RequestMapping(value="/updateView/{fittingId}")
	public ModelAndView updateView(@PathVariable(value = "fittingId") String fittingId){
		ModelAndView mav = new ModelAndView("basicData/hxFittingLocation/hxFittingLocationUpdate");
		HxFittingLocation hxFittingLocation = hxFittingLocationService.getHxFittingLocationById(fittingId);
		mav.addObject("hxFittingLocation", hxFittingLocation);
		return mav;
	}
	
	@RequestMapping(value="/updateHxFittingLocation")
	@ResponseBody
	public String updateHxFittingLocation(HxFittingLocation hxFittingLocation, HttpServletRequest request){
		try {
			hxFittingLocationService.updateHxFittingLocation(hxFittingLocation);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(HxFittingLocation hxFittingLocation, String tableField, String tableHeader, HttpServletRequest request, HttpServletResponse response) throws Exception{
		new DecoderUtil<HxFittingLocation>().decodeURI(hxFittingLocation);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		List<Map<String, Object>> list = hxFittingLocationService.getHxFittingLocationExport(hxFittingLocation);
		ViewExcel viewExcel = new ViewExcel("导出实例" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
		return new ModelAndView(viewExcel);
	}
	
	
	@RequestMapping(value="/showView/{fittingId}")
	public ModelAndView showView(@PathVariable(value = "fittingId") String fittingId){
		ModelAndView mav = new ModelAndView("basicData/hxFittingLocation/hxFittingLocationShow");
		HxFittingLocation hxFittingLocation= hxFittingLocationService.getShowById(fittingId);
		mav.addObject("hxFittingLocation", hxFittingLocation);
		return mav;
	}
	
	
	

}
