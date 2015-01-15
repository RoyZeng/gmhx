package com.gome.gmhx.controller.sysconfig;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.bean.ViewExcel;
import com.gome.common.util.DateUtils;
import com.gome.gmhx.entity.EccGoodsCategory;
import com.gome.gmhx.entity.HxFittingAuth;
import com.gome.gmhx.entity.HxMenu;
import com.gome.gmhx.entity.HxProductClassify;
import com.gome.gmhx.entity.HxRole;
import com.gome.gmhx.entity.HxRoleMenu;
import com.gome.gmhx.service.basicdata.HxProductClassifyService;
import com.gome.gmhx.service.sysconfig.RoleMenuService;
import com.gome.gmhx.service.sysconfig.RoleService;

@Controller
@RequestMapping(value="/hxRoleMenu")
public class HxRoleMenuController {
	
	@Resource
	private RoleMenuService roleMenuService;
	
	@Resource
	private RoleService roleService;
	@Resource
	private HxProductClassifyService hxProductClassifyService;
	
	@RequestMapping(value="/roleMenuView")
	public ModelAndView roleMenuView(HttpServletRequest request){
		String roleId = request.getParameter("roleId");
		ModelAndView mav = new ModelAndView("sysconfig/hxRoleMenu/roleMenuTree");
		HxRole role = roleService.getRoleById(roleId);
		mav.addObject("role", role);
		return mav;
	}
	
	@RequestMapping(value="/getMenuTree", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getMenuTree(){
		List<HxMenu> menuList = roleMenuService.getMenuTree();
        JSONArray array = new JSONArray();
        for (HxMenu menu : menuList) {
            JSONObject object = new JSONObject();
            object.put("id", menu.getMenuId());
            object.put("pId", menu.getParentId());
            object.put("name", menu.getMenuName());
            array.add(object);
        }
		return array.toString();
	}
	
	@RequestMapping(value="/getMenuTreeData", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getMenuTreeData(HttpServletRequest request){
		String roleId = request.getParameter("roleId");
		List<HxMenu> roleList = roleMenuService.getMenuTreeData(roleId);
		JSONArray array = new JSONArray();
	    for (HxMenu menu : roleList) {
	         JSONObject object = new JSONObject();
	         object.put("id", menu.getMenuId());
	         array.add(object);
	     }
		return array.toString();
	}
	
	@RequestMapping(value="addRoleMenu")
	@ResponseBody
	public String addRoleMenu(HxRoleMenu roleMenu) {
        try {
        	String roleidTemp = roleMenu.getRoleId();
        	String menuidTemp = roleMenu.getMenuId();
        	String roleCategoryIdTemp = roleMenu.getRoleCategoryId();
        	String fittingAuthIdTemp = roleMenu.getFittingAuthId();
        	if (StringUtils.isNotEmpty(roleidTemp)) {
        		roleMenuService.deleteCompleteByRoleId(roleidTemp);
        		roleMenuService.deleteFittingAuthByRoleId(roleidTemp);
        		roleMenuService.deleteRoleCategoryByRoleId(roleidTemp);
        	}
        	if (StringUtils.isNotEmpty(roleidTemp)) {
        		if(StringUtils.isNotEmpty(menuidTemp)){
        			String[] menuidTempArr = menuidTemp.split(",");
        			for(int i = 0; i < menuidTempArr.length; i++){
        				HxRoleMenu rm = new HxRoleMenu();
        				rm.setMenuId(menuidTempArr[i]);
        				rm.setRoleId(roleidTemp);
        				roleMenuService.addRoleMenu(rm);
        			}
        		}
    			
    			if (StringUtils.isNotEmpty(roleCategoryIdTemp)) {
    				String[] roleCategoryIdTempArr = roleCategoryIdTemp.split(",");
    				if(roleCategoryIdTempArr.length>0){
    					for(int i = 0; i < roleCategoryIdTempArr.length; i++){
    						HxRoleMenu hxRoleMenu = new HxRoleMenu();
    						if(StringUtils.isNotEmpty(roleCategoryIdTempArr[i])){
    							hxRoleMenu.setRoleCategoryId(roleCategoryIdTempArr[i]);
    							hxRoleMenu.setRoleId(roleidTemp);
    							roleMenuService.addRoleCategory(hxRoleMenu);
    						}
    					}
    				}
    			}	
    			if (StringUtils.isNotEmpty(fittingAuthIdTemp)) {
        			String[] fittingAuthIdTempArr = fittingAuthIdTemp.split(",");
        			if(fittingAuthIdTempArr.length>0){
        				for(int i = 0; i < fittingAuthIdTempArr.length; i++){
        					HxRoleMenu hxRoleMenu = new HxRoleMenu();
            				if(StringUtils.isNotEmpty(fittingAuthIdTempArr[i])){
            					hxRoleMenu.setFittingAuthId(fittingAuthIdTempArr[i]);
            					hxRoleMenu.setRoleId(roleidTemp);
            					roleMenuService.addFittingAuth(hxRoleMenu);
            				}
            			}
        			}
            	}
        	}
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	@RequestMapping(value="/getRoleCategoryTree", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getRoleCategoryTree(){
		List<HxProductClassify> classifyList = hxProductClassifyService.getHxProductClassifyTree();
        JSONArray array = new JSONArray();
        for (HxProductClassify category : classifyList) {
            JSONObject object = new JSONObject();
            object.put("id", category.getClassifyCode());
            object.put("pId", category.getParentCode());
            object.put("name", category.getClassifyName()+"["+category.getClassifyCode()+"]");//显示岗位品类名称和代码
            array.add(object);
        }
		return array.toString();
	}
	
	@RequestMapping(value="/getRoleCategoryTreeData", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getRoleCategoryTreeData(HttpServletRequest request){
		String roleId = request.getParameter("roleId");
		List<Map> classifyList = hxProductClassifyService.getHxProductClassifyTreeData(roleId);
		JSONArray array = new JSONArray();
	    for (Map category : classifyList) {
	         JSONObject object = new JSONObject();
	         if(category!=null){
	        	 object.put("id", category.get("roleCategory"));
		         array.add(object);
	         }
	     }
		return array.toString();
	}
	
	@RequestMapping(value="/getFittingAuthTree", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getFittingAuthTree(HttpServletRequest request){
		List<HxFittingAuth> fittingAuthList = roleMenuService.getFittingAuthTree();
        JSONArray array = new JSONArray();
        for (HxFittingAuth hxFittingAuth : fittingAuthList) {
            JSONObject object = new JSONObject();
            object.put("id", hxFittingAuth.getFittingAuthId());
            object.put("name", hxFittingAuth.getFittingAuthName());
            array.add(object);
        }
		return array.toString();
	}
	
	@RequestMapping(value="/getFittingAuthTreeData", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getFittingAuthTreeData(HttpServletRequest request){
		String roleId = request.getParameter("roleId");
		List<HxFittingAuth> fittingAuthList = roleMenuService.getFittingAuthTreeData(roleId);
		JSONArray array = new JSONArray();
	    for (HxFittingAuth hxFittingAuth : fittingAuthList) {
	         JSONObject object = new JSONObject();
	         if(hxFittingAuth!=null){
	        	 object.put("id", hxFittingAuth.getFittingAuthId());
		         array.add(object);
	         }
	     }
		return array.toString();
	}
	
	@RequestMapping(value="/exportRoleAuthorityExcel")
	public ModelAndView exportRoleAuthorityExcel(HxRole hxRole) throws Exception{
		String tableField="roleName|roleDesc|menuName|fittingAuthName";
		String header="角色名称|角色描述|菜单权限|物料权限";
		List<Map<String, Object>> list = roleMenuService.getHxRoleAuthorityExport(hxRole);
		for(Map<String, Object> map:list){
			Object fittingAuth=map.get("fittingAuthName");
			if(fittingAuth!=null && !"".equals(fittingAuth.toString().trim())){
				String fittingAuthName=(String) fittingAuth;
				String splits[]=fittingAuthName.split(",");
				Set<String> set=new HashSet<String>();
				if(splits!=null && splits.length>0){
					for(String str:splits){
						set.add(str);
					}
				}
				String fittauthConn="";
				for(String fitauth:set){
					fittauthConn+=fitauth+",";
				}
				fittauthConn=fittauthConn.substring(0, fittauthConn.length()-1);
				map.put("fittingAuthName", fittauthConn);
			}else{
				map.put("fittingAuthName", "");
			}
		}
		ViewExcel viewExcel = new ViewExcel("导出实例" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE), tableField, header, list);
		return new ModelAndView(viewExcel);
	}
	
}