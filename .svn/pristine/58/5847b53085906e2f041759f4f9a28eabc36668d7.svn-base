package com.gome.gmhx.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.Constrants;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.jbpm.JbpmService;

@Controller
@RequestMapping(value="/index")
public class IndexController {
	
	@Resource
	JbpmService jbpmService;
	
	@RequestMapping(value="/getNorth")
	public String getNorth(){
		return "layout/north";
	}
	
	@RequestMapping(value="/getWest")
	public ModelAndView getWest(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("layout/west");
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		int fromType = sysUser.getFromType();
		List<String> ids = jbpmService.findAllMyTaskWorkEntityId();//查询当前任务数量
		int works = 0;
		if(ids!=null){
			if(ids.size()>0){
				works = ids.size();
			}
		}
		mav.addObject("works", works);
		mav.addObject("fromType", fromType);
		return mav;
	}
	
	@RequestMapping(value="/getMain")
	public String getMain(){
		return "main";
	}
	
	@RequestMapping(value="/getWorks", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getWorks(){
		List<String> ids = jbpmService.findAllMyTaskWorkEntityId();//查询当前任务数量
        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();
        int works = 0;
		if(ids!=null){
			if(ids.size()>0){
				works = ids.size();
			}
		}
        object.put("works", works);
        array.add(object);
		return array.toString();
	}
}
