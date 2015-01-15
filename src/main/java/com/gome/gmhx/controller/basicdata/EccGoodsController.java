package com.gome.gmhx.controller.basicdata;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.common.page.Page;
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.EccGoods;
import com.gome.gmhx.service.wsdl.service.EccService;

@Controller
@RequestMapping(value="/eccGoods")
public class EccGoodsController {
	@Resource
	EccService eccService;
	
	@RequestMapping(value="/eccGoodsView")
	public String brandView(){
		return "basicData/eccGoods/goodsList";
	}
	@RequestMapping(value="/getGoodClass",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getGoodClass(String classification,String upperCode){
		if(!"1".equals(classification)&&StringUtils.isEmpty(upperCode)){
			return "[]";
		}
		List data = this.eccService.getEccGoodsCategory(classification, upperCode);
		if(data==null){
			return "[]";
		}
		return JsonUtil.toJson(data);
	}
	@RequestMapping(value="/getFaultByCategory",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getFaultByCategory(String category){
		List data = this.eccService.getFaultByCategory(category);
		if(data==null){
			return "[]";
		}
		return JsonUtil.toJson(data);
	}
	
	@RequestMapping(value="/getGoodClassPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getGoodClassPageList(Page page, EccGoods goods) throws Exception{
		page.setParam(goods);
		List<Map<String, Object>> list = eccService.getGoodClassPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}

}
