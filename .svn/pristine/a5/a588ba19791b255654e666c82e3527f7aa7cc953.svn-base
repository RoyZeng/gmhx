package com.gome.gmhx.controller.sysconfig;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.gmhx.entity.HxRole;
import com.gome.gmhx.entity.HxUser;
import com.gome.gmhx.entity.HxUserRole;
import com.gome.gmhx.service.sysconfig.UserRoleService;
import com.gome.gmhx.service.sysconfig.HxUserService;

@Controller
@RequestMapping(value="/hxUserRole")
public class HxUserRoleController {
	
	@Resource
	private UserRoleService userRoleService;
	
	@Resource
	private HxUserService userService;
	
	@RequestMapping(value="/userRoleView/{userLoginName}")
	public ModelAndView userRoleView(@PathVariable(value = "userLoginName") String userLoginName){
		ModelAndView mav = new ModelAndView("sysconfig/hxUserRole/userRoleTree");
		HxUser user = userService.getUserById(userLoginName);
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping(value="/getRoleTree", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getRoleTree(){
		List<HxRole> roleList = userRoleService.getRoleTree();
        JSONArray array = new JSONArray();
        for (HxRole role : roleList) {
            JSONObject object = new JSONObject();
            object.put("id", role.getRoleId());
            object.put("pId", "");
            object.put("name", role.getRoleName());
            array.add(object);
        }
		return array.toString();
	}
	
	@RequestMapping(value="/getRoleTreeData/{userId}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getRoleTreeData(@PathVariable(value = "userId") String userId){
		List<HxRole> roleList = userRoleService.getRoleTreeData(userId);
		JSONArray array = new JSONArray();
	    for (HxRole role : roleList) {
	         JSONObject object = new JSONObject();
	         object.put("id", role.getRoleId());
	         array.add(object);
	     }
		return array.toString();
	}
	
	@RequestMapping(value="addUserRole")
	@ResponseBody
	public String addUserRole(HxUserRole userRole) {
        try {
        	String roleidTemp = userRole.getRoleId();
        	String useridTemp = userRole.getUserId();
        	if (StringUtils.isNotEmpty(useridTemp)) {
        		userRoleService.deleteCompleteByUserId(useridTemp);
        	}
        	if (StringUtils.isNotEmpty(roleidTemp)) {
    			String[] roleidArr = roleidTemp.split(",");
    			for(int i = 0; i < roleidArr.length; i++){
    				HxUserRole ur = new HxUserRole();
    				ur.setRoleId(roleidArr[i]);
    				ur.setUserId(useridTemp);
    				userRoleService.addUserRole(ur);
    			}
    				
        	}
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
}