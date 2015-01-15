package com.gome.gmhx.controller.basicdata;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.common.util.BeanMapUtils;
import com.gome.gmhx.entity.HxProductClassify;
import com.gome.gmhx.service.basicdata.HxProductClassifyService;

@Controller
@RequestMapping(value="/hxProductClassify")
public class HxProductClassifyController {
	@Resource
	private HxProductClassifyService hxProductClassifyService;
	
	@RequestMapping(value="/hxProductClassifyView")
	public String hxCodeView(){
		return "basicData/hxProductClassify/hxProductClassifyList";
	}
	
	@RequestMapping(value="/getHxProductClassifyTree", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getHxProductClassifyTree(){
		List<HxProductClassify> classifyList = hxProductClassifyService.getHxProductClassifyTree();
        JSONArray array = new JSONArray();
        for (HxProductClassify hxProductClassify : classifyList) {
            JSONObject object = new JSONObject();
            object.put("id", hxProductClassify.getClassifyCode());
            object.put("pId", hxProductClassify.getParentCode());
            object.put("power", hxProductClassify.getPower());
            object.put("name", hxProductClassify.getClassifyName()+"["+hxProductClassify.getClassifyCode()+"]");
            object.put("open", false);
            array.add(object);
        }
		return array.toString();
	}
	
	@RequestMapping(value="/createHxProductClassify", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String createHxProductClassify(HxProductClassify hxProductClassify){
		try{
			hxProductClassifyService.createHxProductClassify(hxProductClassify);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "failure";
		}
	}
	
	@RequestMapping(value="/updateHxProductClassify", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String updateHxProductClassify(HxProductClassify hxProductClassify, String classifyCodeOld){
		try{
			Map<String, Object> map = BeanMapUtils.convertBean(hxProductClassify);
			map.put("classifyCodeOld", classifyCodeOld);
			hxProductClassifyService.updateHxProductClassify(map);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "failure";
		}
	}
	
	@RequestMapping(value="/delHxProductClassify", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String delHxProductClassify(String classifyCode){
		try{
			hxProductClassifyService.delHxProductClassify(classifyCode);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "failure";
		}
	}
	
}
