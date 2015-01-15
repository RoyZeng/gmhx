package com.gome.gmhx.controller.sysconfig;

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
import com.gome.gmhx.entity.HxPostage;
import com.gome.gmhx.entity.HxRole;
import com.gome.gmhx.service.sysconfig.RoleService;

@Controller
@RequestMapping(value="/hxRole")
public class HxRoleController {
	@Resource
	private RoleService roleService;
	
	@RequestMapping(value="/roleView")
	public String roleView(){
		return "sysconfig/hxRole/roleList";
	}
	
	@RequestMapping(value="/getRolePageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getRoleList(HttpServletResponse response, Page page, HxRole role) throws Exception{
		page.setParam(role);
		List<Map<String, Object>> list = roleService.getRolePageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/addView")
	public String addView(){
		return "sysconfig/hxRole/roleAdd";
	}
	
	@RequestMapping(value="addRole")
	@ResponseBody
	public String addRole(HxRole role) {
        try {
        	roleService.addRole(role);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  "{\"flag\" : \"fail\"}";
    }
	
	@RequestMapping(value="/updateView")
	public ModelAndView updateView(){
		ModelAndView mav = new ModelAndView("sysconfig/hxRole/roleUpdate");
		return mav;
	}
	
	@RequestMapping(value="/updateRole")
	@ResponseBody
	public String updateRole(HxRole role){
		try {
			roleService.updateRole(role);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"flag\" : \"fail\"}";
	}
	
	@RequestMapping(value="/showView")
	public ModelAndView showView(@PathVariable(value = "roleId") String roleId){
		ModelAndView mav = new ModelAndView("sysconfig/hxRole/roleShow");
		return mav;
	}
	
	@RequestMapping(value="/exportExcel")
	public ModelAndView exportExcel(HxRole hxRole, String tableField, String tableHeader, HttpServletRequest request, HttpServletResponse response) throws Exception{
		new DecoderUtil<HxRole>().decodeURI(hxRole);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		List<Map<String, Object>> list = roleService.getHxRoleExport(hxRole);
		ViewExcel viewExcel = new ViewExcel("导出实例" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@:"+tableField+header);
		return new ModelAndView(viewExcel);
	}
}