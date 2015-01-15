package com.gome.gmhx.controller;

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
import com.gome.gmhx.entity.Demo;
import com.gome.gmhx.service.DemoService;

@Controller
@RequestMapping(value="/demo")
public class DemoController {
	@Resource
	private DemoService demoService;
	
	@RequestMapping(value="/demoView")
	public String demoView(){
		return "demo/demoList";
	}
	
	@RequestMapping(value="/getDemoPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getDemoPageList(HttpServletResponse response, Page page, Demo demo) throws Exception{
		page.setParam(demo);
		List<Map<String, Object>> list = demoService.getDemoPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/addView")
	public String addView(){
		return "demo/demoAdd";
	}
	
	@RequestMapping(value="/addDemo")
	@ResponseBody
	public String addDemo(Demo demo) {
        try {
        	demoService.addDemo(demo);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	@RequestMapping(value="/updateView/{aa}")
	public ModelAndView updateView(@PathVariable(value = "aa") String aa){
		ModelAndView mav = new ModelAndView("demo/demoUpdate");
		Demo demo = demoService.getDemoById(aa);
		mav.addObject("demo", demo);
		return mav;
	}
	
	@RequestMapping(value="/updateDemo")
	@ResponseBody
	public String updateDemo(Demo demo){
		try {
        	demoService.updateDemo(demo);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Demo demo, String tableField, String tableHeader, HttpServletRequest request, HttpServletResponse response) throws Exception{
		new DecoderUtil<Demo>().decodeURI(demo);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		List<Map<String, Object>> list = demoService.getDemoExport(demo);
		ViewExcel viewExcel = new ViewExcel("DEMO导出实例" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
		return new ModelAndView(viewExcel);
	}
	
	@RequestMapping(value="/demoProgress")
	public String demoProgress(){
		return "demo/demoProgress";
	}
}