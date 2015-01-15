package com.gome.gmhx.controller.servicemanage;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.common.util.JsonUtil;
import com.gome.gmhx.service.servicemanage.HxProgressInfoService;

@Controller
@RequestMapping(value="/progress")
public class HxProgressController {
	
	@Resource
	HxProgressInfoService progressInfoService;
	
	@RequestMapping(value="/getProgress/{serviceId}")
	@ResponseBody
	public String getProgress(@PathVariable String serviceId){
		List<Map<String, Object>> progresses = progressInfoService.getProgressInfo(serviceId);
		return JsonUtil.toJson(progresses);
	}
	
}
