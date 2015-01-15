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
import com.gome.gmhx.entity.HxBarCodeRules;
import com.gome.gmhx.service.basicdata.HxBarCodeRulesService;

@Controller
@RequestMapping(value="/hxBarCodeRules")
public class HxBarCodeRulesController {
	@Resource
	private HxBarCodeRulesService hxBarCodeRulesService;
	
	@RequestMapping(value="/hxBarCodeRulesView")
	public String hxBarCodeRulesView(){
		return "basicData/hxBarCodeRules/hxBarCodeRulesList";
	}
	                        
	@RequestMapping(value="/getHxBarCodeRulesPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxBarCodeRulesPageList(Page page, HxBarCodeRules hxBarCodeRules,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date modifieDate_sta,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date modifieDate_end) throws Exception{
		Map <String , Object> map =BeanMapUtils.convertBean(hxBarCodeRules);
		map.put("modifieDate_sta", modifieDate_sta);
		map.put("modifieDate_sta", modifieDate_sta);
		page.setParam(map);
		List<Map<String, Object>> list = hxBarCodeRulesService.getHxBarCodeRulesPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/addView")
	public String addView(){
		return "basicData/hxBarCodeRules/hxBarCodeRulesAdd";
	}
	
	@RequestMapping(value="/addHxBarCodeRules")
	@ResponseBody
	public String addHxBarCodeRules(HxBarCodeRules hxBarCodeRules, HttpServletRequest request) {
        try {	
        	hxBarCodeRulesService.addHxBarCodeRules(hxBarCodeRules);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	@RequestMapping(value="/updateView/{rulesId}")
	public ModelAndView updateView(@PathVariable(value = "rulesId") String rulesId){
		ModelAndView mav = new ModelAndView("basicData/hxBarCodeRules/hxBarCodeRulesUpdate");
		HxBarCodeRules hxBarCodeRules = hxBarCodeRulesService.getHxBarCodeRulesById(rulesId);
		mav.addObject("hxBarCodeRules", hxBarCodeRules);
		return mav;
	}
	
	@RequestMapping(value="/updateHxBarCodeRules")
	@ResponseBody
	public String updateHxBarCodeRules(HxBarCodeRules hxBarCodeRules, HttpServletRequest request){
		try {
			hxBarCodeRulesService.updateHxBarCodeRules(hxBarCodeRules);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(HxBarCodeRules hxBarCodeRules, String tableField, String tableHeader, HttpServletRequest request, HttpServletResponse response) throws Exception{
		new DecoderUtil<HxBarCodeRules>().decodeURI(hxBarCodeRules);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		List<Map<String, Object>> list = hxBarCodeRulesService.getHxBarCodeRulesExport(hxBarCodeRules);
		ViewExcel viewExcel = new ViewExcel("导出实例" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
		return new ModelAndView(viewExcel);
	}
	
	
	@RequestMapping(value="/showView/{rulesId}")
	public ModelAndView showView(@PathVariable(value = "rulesId") String rulesId){
		ModelAndView mav = new ModelAndView("basicData/hxBarCodeRules/hxBarCodeRulesShow");
		HxBarCodeRules hxBarCodeRules= hxBarCodeRulesService.getShowById(rulesId);
		mav.addObject("hxBarCodeRules", hxBarCodeRules);
		return mav;
	}
	
	
	

}
