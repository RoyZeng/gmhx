package com.gome.gmhx.controller.sysconfig;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.gmhx.entity.HxFittingAuth;
import com.gome.gmhx.entity.HxMenu;
import com.gome.gmhx.entity.HxPosition;
import com.gome.gmhx.entity.HxPositionMenu;
import com.gome.gmhx.service.sysconfig.HxPositionMenuService;
import com.gome.gmhx.service.sysconfig.HxPositionService;

@Controller
@RequestMapping(value="/hxPositionMenu")
public class HxPositionMenuController {
	
	@Resource
	private HxPositionMenuService hxPositionMenuService;
	
	@Resource
	private HxPositionService hxPositionService;
	
	@RequestMapping(value="/positionMenuView/{positionCode}")
	public ModelAndView positionMenuView(@PathVariable(value = "positionCode") String positionCode,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("sysconfig/hxPositionMenu/positionMenuTree");
		HxPosition position = null;
		String fromType = request.getParameter("fromType");
		if("0".equals(fromType)){//系统自建岗位
			position = hxPositionService.getPositionById(positionCode);
		}else if("1".equals(fromType)){//身份管理平台岗位
			List<HxPosition> positionList = hxPositionService.getPositionByCode(positionCode);
			if(positionList!=null){
				if(positionList.size()>0){
					position = positionList.get(0);
				}
			}
		}
		mav.addObject("position", position);
		return mav;
	}
	
	@RequestMapping(value="/getMenuTree", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getMenuTree(){
		List<HxMenu> menuList = hxPositionMenuService.getMenuTree();
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
	
	@RequestMapping(value="/getMenuTreeData/{positionCode}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getMenuTreeData(@PathVariable(value = "positionCode") String positionCode){
		List<HxMenu> menuList = hxPositionMenuService.getMenuTreeData(positionCode);
		JSONArray array = new JSONArray();
	    for (HxMenu menu : menuList) {
	         JSONObject object = new JSONObject();
	         if(menu!=null){
	        	 object.put("id", menu.getMenuId());
		         array.add(object);
	         }
	     }
		return array.toString();
	}
	
	@RequestMapping(value="addPositionMenu")
	@ResponseBody
	public String addPositionMenu(HxPositionMenu positionMenu) {
        try {
        	String positionidTemp = positionMenu.getPositionId();
        	String menuidTemp = positionMenu.getMenuId();
        	String fittingAuthIdTemp = positionMenu.getFittingAuthId();
        	if (StringUtils.isNotEmpty(positionidTemp)) {
        			hxPositionMenuService.deleteCompleteByPositionId(positionidTemp);
        			hxPositionMenuService.deleteFittingAuthByPositionId(positionidTemp);
        	}
        	if (StringUtils.isNotEmpty(positionidTemp)) {
    			String[] menuidTempArr = menuidTemp.split(",");
    			if(menuidTempArr.length>0){
    				for(int i = 0; i < menuidTempArr.length; i++){
        				HxPositionMenu pm = new HxPositionMenu();
        				if(StringUtils.isNotEmpty(menuidTempArr[i])){
        					pm.setMenuId(menuidTempArr[i]);
            				pm.setPositionId(positionidTemp);
            				hxPositionMenuService.addPositionMenu(pm);
        				}
        			}
    			}
        	}
        	if (StringUtils.isNotEmpty(positionidTemp)) {
    			String[] fittingAuthIdTempArr = fittingAuthIdTemp.split(",");
    			if(fittingAuthIdTempArr.length>0){
    				for(int i = 0; i < fittingAuthIdTempArr.length; i++){
        				HxPositionMenu pm = new HxPositionMenu();
        				if(StringUtils.isNotEmpty(fittingAuthIdTempArr[i])){
        					pm.setFittingAuthId(fittingAuthIdTempArr[i]);
            				pm.setPositionId(positionidTemp);
            				hxPositionMenuService.addFittingAuth(pm);
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
	
	@RequestMapping(value="/getFittingAuthTree", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getFittingAuthTree(HttpServletRequest request){
		String positionType = request.getParameter("positionType");
		String param ="";
		if(StringUtils.isNotBlank(positionType)){
			if("1".equals(positionType)){
				param = "zb";
			}else if("2".equals(positionType)){
				param = "fb";
			}else if("3".equals(positionType)){
				param = "wd";
			}
		}
		List<HxFittingAuth> fittingAuthList = hxPositionMenuService.getFittingAuthTree(param);
        JSONArray array = new JSONArray();
        for (HxFittingAuth hxFittingAuth : fittingAuthList) {
            JSONObject object = new JSONObject();
            object.put("id", hxFittingAuth.getFittingAuthId());
            object.put("name", hxFittingAuth.getFittingAuthName());
            array.add(object);
        }
		return array.toString();
	}
	
	@RequestMapping(value="/getFittingAuthTreeData/{positionCode}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getFittingAuthTreeData(@PathVariable(value = "positionCode") String positionCode){
		List<HxFittingAuth> fittingAuthList = hxPositionMenuService.getFittingAuthTreeData(positionCode);
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
	
}