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
import com.gome.common.util.UUIDGenerator;
import com.gome.gmhx.entity.HxFree;
import com.gome.gmhx.service.basicdata.HxFreeService;

@Controller
@RequestMapping(value="/StandardFree")
public class HxFreeController {
	@Resource
	private HxFreeService freeService;
	
	//跳主界面
	@RequestMapping(value="/StandardFreeView")
	public String StandardFreeView(){
		return "basicData/hxInstallStandardFee/hxStandardFreeList";	
	}
	
	@RequestMapping(value="/getFreePageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getFreePageList(HttpServletResponse response,Page page, HxFree free) throws Exception{
		page.setParam(free);
		List<Map<String,Object>> list = freeService.getFreePageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);	
		}
  
	@RequestMapping(value="addView")
	public String AddView(){
		return  "basicData/hxInstallStandardFee/hxStandardFreeAdd";	
	}
	
	@RequestMapping(value="addHxFree")
	@ResponseBody
	public String addHxFree(HxFree free){
	try{
		String freeId = UUIDGenerator.getUUID();
		free.setFreeId(freeId);
		freeService.addHxFree(free);
		   return "{\"flag\" : \"success\"}";
       } catch (Exception e) {
        e.printStackTrace();
      }
    return null;
	}
	
	@RequestMapping(value="/updateView/{freeId}")
	public ModelAndView updateView(@PathVariable(value = "freeId") String aa){
		ModelAndView mav = new ModelAndView("basicData/hxInstallStandardFee/hxStandardFreeUpdate");
		HxFree free = freeService.getHxFreeById(aa);
		mav.addObject("free", free);
		return mav;
	}
	 
	@RequestMapping(value="/updateHxFree")
	@ResponseBody
	public String updateHxFree(HxFree free){
		try {
		//	brand.setModifier(((SysUser)request.getSession().getAttribute(Constrants.USER_INFO)).getUserName());
			freeService.updateHxFree(free);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@RequestMapping(value="/showView/{freeId}")
	public ModelAndView showView(@PathVariable(value = "freeId") String id){
		ModelAndView mav = new ModelAndView("basicData/hxInstallStandardFee/hxStandardFreeShow");
		HxFree free = freeService.getShowById(id);
		mav.addObject("free", free);
		return mav;
	}
	
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(HxFree free, String tableField, String tableHeader, HttpServletRequest request, HttpServletResponse response) throws Exception{
		new DecoderUtil<HxFree>().decodeURI(free);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		List<Map<String, Object>> list = freeService.getHxFreeExport(free);
		ViewExcel viewExcel = new ViewExcel("结算标准资料导出" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
		return new ModelAndView(viewExcel);
	}
	
	
}