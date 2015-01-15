package com.gome.gmhx.controller.basicdata;


import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.gome.gmhx.entity.Brand;
import com.gome.gmhx.jbpm.CurrentSysUser;
import com.gome.gmhx.service.basicdata.BrandService;


@Controller
@RequestMapping(value="/brand")
public class BrandController {
	
	@Resource
	private BrandService brandService;
	
	@RequestMapping(value="/brandView")
	public String brandView(){
		return "basicData/brandinformation/BrandinformationList";
		
	}
	
	@RequestMapping(value="/getBrandPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getBrandList(Page page,Brand brand,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_date_st,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date mod_date_end)
	       throws Exception{
		Map <String , Object> map =BeanMapUtils.convertBean(brand);
		map.put("mod_date_st", mod_date_st);
		map.put("mod_date_end", mod_date_end);
		page.setParam(map);
		List<Map<String, Object>> list = brandService.getBrandPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);	
	}


	
	@RequestMapping(value="/addView")
	public String addView(){
		return "basicData/brandinformation/BrandinformationAdd";
	}
	
	@RequestMapping(value="addBrand")
	@ResponseBody
	public String addBrand(Brand brand) {
        try {
 
			//brand.setCreater(((SysUser)request.getSession().getAttribute(Constrants.USER_INFO)).getUserName());
        	brand.setRep_date(new Date());//设置创建日期
        	brandService.addBrand(brand);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	@RequestMapping(value="/updateView/{id}")
	public ModelAndView updateView(@PathVariable(value = "id") String aa){
		ModelAndView mav = new ModelAndView("basicData/brandinformation/BrandinformationUpdate");
		Brand brand = brandService.getBrandById(aa);
		mav.addObject("brand", brand);
		return mav;
	}
	
	@RequestMapping(value="/updateBrand",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String updateBrand(Brand brand){
		try {
			brand.setModifier(CurrentSysUser.getCurrentSysUser().getUserAccount());
			brand.setMod_date(new Date());//设置创建日期
			brandService.setIsUse(brand);
            return "{\"flag\": \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"flag\":\"fail\"}";
	}
	
	@RequestMapping(value="/showView/{id}")
	public ModelAndView showView(@PathVariable(value = "id") String id){
		ModelAndView mav = new ModelAndView("basicData/brandinformation/BrandinformationShow");
		Brand brand = brandService.getShowById(id);
		mav.addObject("brand", brand);
		return mav;
	}
	
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(Brand brand, String tableField, String tableHeader, HttpServletRequest request, HttpServletResponse response) throws Exception{
		new DecoderUtil<Brand>().decodeURI(brand);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		List<Map<String, Object>> list = brandService.getBrandExport(brand);
		ViewExcel viewExcel = new ViewExcel("商品厂家信息导出" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
		return new ModelAndView(viewExcel);
	}
	
	@RequestMapping(value="/getAllBrand",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getAllBrand(String classification,String upperCode){
		List<Map> data = new ArrayList<Map>();
		Map m = new HashMap();
		m.put("id","");
		data.add(m);
		data.addAll(this.brandService.getAllBrand());		
		return JsonUtil.toJson(data);
	}
	
	
}