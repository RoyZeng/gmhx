package com.gome.common.bean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/validate")
public class ValidateController {
	/*
	 * 验证机构
	 */
	@RequestMapping("/jg/{value}")
	@ResponseBody
	public String validateJG(@PathVariable String value){
		return null;
	}
	
	/*
	 * 验证机型
	 */
	@RequestMapping("/jx/{value}")
	@ResponseBody
	public String validateJX(@PathVariable String value){
		return null;
	}
}
