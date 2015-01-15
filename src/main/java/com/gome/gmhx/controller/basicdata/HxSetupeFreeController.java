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
import com.gome.gmhx.entity.HxSetupeFree;
import com.gome.gmhx.service.basicdata.HxSetupeFreeService;

@Controller
@RequestMapping(value="/StandardSetupFree")
public class HxSetupeFreeController {
	
	@Resource
	private HxSetupeFreeService setFreeSerivce;
	
	//跳主界面
	@RequestMapping(value="/StandardSetupFreeView")
	public String StandardSetupFreeView(){
		return "basicData/hxInstallStandardSetupFree/hxStandardSetupFreeList";	
	} 

	//分页界面
	@RequestMapping(value="/getSetupFreePageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getSetupFreePageList(HttpServletResponse response,Page page, HxSetupeFree free)throws Exception{
		page.setParam(free);
		List<Map<String,Object>> list = setFreeSerivce.getSetupFreePageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);	
		}
	
	@RequestMapping(value="/addView")
	public String AddView(){
		return  "basicData/hxInstallStandardSetupFree/hxStandardSetupFreeAdd";	
	}
	
	@RequestMapping(value="addHxSetupeFree")
	@ResponseBody
	public String addHxSetupeFree(HxSetupeFree free){
	try{
		 String freeCode = UUIDGenerator.getUUID();
		 free.setFreeCode(freeCode);//设置主键
		 setFreeSerivce.addHxSetupeFree(free);
		   return "{\"flag\" : \"success\"}";
       } catch (Exception e) {
        e.printStackTrace();
      }
    return null;
	}
	
	@RequestMapping(value="/updateView/{freeCode}")
	public ModelAndView updateView(@PathVariable(value = "freeCode") String aa){
		ModelAndView mav = new ModelAndView("basicData/hxInstallStandardSetupFree/hxStandardSetupFreeUpdate");
		HxSetupeFree free = setFreeSerivce.getHxSetupeFreeById(aa);
		mav.addObject("free", free);
		return mav;
	}
	 
	@RequestMapping(value="/updateHxSetupeFree")
	@ResponseBody
	public String updateHxSetupeFree(HxSetupeFree free){
		try {
		//	brand.setModifier(((SysUser)request.getSession().getAttribute(Constrants.USER_INFO)).getUserName());
			setFreeSerivce.updateHxSetupeFree(free);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@RequestMapping(value="/showView/{freeCode}")
	public ModelAndView showView(@PathVariable(value = "freeCode") String id){
		ModelAndView mav = new ModelAndView("basicData/hxInstallStandardSetupFree/hxStandardSetupFreeShow");
		HxSetupeFree free = setFreeSerivce.getShowById(id);
		mav.addObject("free", free);
		return mav;
	}
	
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(HxSetupeFree free, String tableField, String tableHeader, HttpServletRequest request, HttpServletResponse response) throws Exception{
		new DecoderUtil<HxSetupeFree>().decodeURI(free);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		List<Map<String, Object>> list = setFreeSerivce.getHxSetupeFreeExport(free);
		ViewExcel viewExcel = new ViewExcel("结算调整标准资料导出" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
		return new ModelAndView(viewExcel);
	}
	
	
}