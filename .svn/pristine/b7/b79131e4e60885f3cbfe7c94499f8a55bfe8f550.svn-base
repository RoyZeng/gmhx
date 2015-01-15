package com.gome.gmhx.controller.sysconfig;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.Constrants;
import com.gome.common.page.Page;
import com.gome.common.util.JsonUtil;
import com.gome.common.util.UUIDGenerator;
import com.gome.gmhx.entity.HxMenu;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.service.sysconfig.HxMenuService;

@Controller
@RequestMapping(value="/hxMenu")
public class HxMenuController {
	@Resource
	private HxMenuService hxMenuService;
	
	@RequestMapping(value="/getPositionRoleMenuTree", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getPositionRoleMenuTree(HttpServletRequest request){
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		String sysPositionId = sysUser.getSysPositionId();//系统岗位Id
		List<HxMenu> menuList = hxMenuService.getPositionRoleMenuTree(sysPositionId);
        JSONArray array = new JSONArray();
        for (HxMenu menu : menuList) {
            JSONObject object = new JSONObject();
            object.put("id", menu.getMenuId());
            object.put("pId", menu.getParentId());
            object.put("name", menu.getMenuName());
            object.put("lnkUrl", menu.getUrl());
            object.put("icon", menu.getIconUrl());
            if (String.valueOf(menu.getSort()).length() == 4) {
                object.put("open", true);
            }
            array.add(object);
        }
		return array.toString();
	}
	
	@RequestMapping(value="/getPositionMenuTree", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getPositionMenuTree(HttpServletRequest request){
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constrants.USER_INFO);
		String sysPositionId = sysUser.getSysPositionId();//系统岗位Id
		List<HxMenu> menuList = hxMenuService.getPositionMenuTree(sysPositionId);
        JSONArray array = new JSONArray();
        for (HxMenu menu : menuList) {
            JSONObject object = new JSONObject();
            object.put("id", menu.getMenuId());
            object.put("pId", menu.getParentId());
            object.put("name", menu.getMenuName());
            object.put("lnkUrl", menu.getUrl());
            object.put("icon", menu.getIconUrl());
            if (String.valueOf(menu.getSort()).length() == 4) {
                object.put("open", true);
            }
            array.add(object);
        }
		return array.toString();
	}
	
	@RequestMapping(value="/getMenuTree", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getMenuTree(){
		List<HxMenu> menuList = hxMenuService.getMenuTree();
        JSONArray array = new JSONArray();
        for (HxMenu menu : menuList) {
            JSONObject object = new JSONObject();
            object.put("id", menu.getMenuId());
            object.put("pId", menu.getParentId());
            object.put("name", menu.getMenuName());
            object.put("lnkUrl", menu.getUrl());
            object.put("icon", menu.getIconUrl());
            if (String.valueOf(menu.getSort()).length() == 4) {
                object.put("open", true);
            }
            array.add(object);
        }
		return array.toString();
	}
	
	@RequestMapping(value="/menuView")
	public String menuView(){
		return "sysconfig/hxMenu/menuList";
	}
	
	@RequestMapping(value="/getMenuPageList", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getMenuList(HttpServletResponse response, Page page, HxMenu menu) throws Exception{
		page.setParam(menu);
		List<Map<String, Object>> list = hxMenuService.getMenuPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/addView")
	public String addView(){
		return "sysconfig/hxMenu/menuAdd";
	}
	
	@RequestMapping(value="/addMenu")
	@ResponseBody
	public String addMenu(HxMenu menu) {
        try {
        	String menuId = UUIDGenerator.getUUID();
        	menu.setMenuId(menuId);//设置主键
        	hxMenuService.addMenu(menu);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	@RequestMapping(value="/updateView")
	public ModelAndView updateView(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("sysconfig/hxMenu/menuUpdate");
		String menuId = request.getParameter("menuId");
		HxMenu menu = hxMenuService.getMenuById(menuId);
		mav.addObject("menu", menu);
		return mav;
	}
	
	@RequestMapping(value="/updateMenu")
	@ResponseBody
	public String updateMenu(HxMenu menu){
		try {
			hxMenuService.updateMenu(menu);
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@RequestMapping(value="/showView")
	public ModelAndView showView(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("sysconfig/hxMenu/menuShow");
		String menuId = request.getParameter("menuId");
		HxMenu menu = hxMenuService.getMenuById(menuId);
		mav.addObject("menu", menu);
		return mav;
	}
	
	@RequestMapping(value="/chooseMenu")
	public String chooseMenu(){
		return "sysconfig/hxMenu/menuTree";
	}
}
