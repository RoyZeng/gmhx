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

import com.gome.gmhx.entity.HxPosition;
import com.gome.gmhx.entity.HxUser;
import com.gome.gmhx.entity.HxUserPosition;
import com.gome.gmhx.entity.SysUser;
import com.gome.gmhx.service.sysconfig.HxUserPositionService;
import com.gome.gmhx.service.sysconfig.HxUserService;
import com.gome.gmhx.service.wsdl.service.EmpService;
import com.gome.gmhx.service.wsdl.service.JlAccountService;

@Controller
@RequestMapping(value="/hxUserPosition")
public class HxUserPositionController {
	
	@Resource
	private HxUserPositionService hxUserPositionService;
	
	@Resource
	private HxUserService userService;
	
	@Resource
	private EmpService empService;
	
	@Resource
	private JlAccountService jlAccountService;
	
	@RequestMapping(value="/userPositionView/{userLoginName}")
	public ModelAndView userPositionView(@PathVariable(value = "userLoginName") String userLoginName, String pageMarkup, String fromType,String origin){
		ModelAndView mav = new ModelAndView("sysconfig/hxUserPosition/userPositionTree");
		SysUser sysuser = null ;
		if("3".equals(fromType)){
			HxUser user = userService.getUserById(userLoginName);
			sysuser = new SysUser();
			sysuser.setUserAccount(user.getUserLoginName());
			sysuser.setUserName(user.getUserName());
			sysuser.setFromType(3);
		}else if("2".equals(fromType)){
			sysuser = jlAccountService.selectAccountByPrimaryKey(userLoginName);
		}else if("1".equals(fromType)){
			sysuser = empService.getAccountByApAccount(userLoginName);
		}
		mav.addObject("sysuser", sysuser);
		mav.addObject("pageMarkup", pageMarkup);
		mav.addObject("origin", origin);
		return mav;
	}
	
	@RequestMapping(value="/getPositionTree", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getPositionTree(){
		List<HxPosition> positionList = hxUserPositionService.getPositionTree();
        JSONArray array = new JSONArray();
        for (HxPosition position : positionList) {
            JSONObject object = new JSONObject();
            object.put("id", position.getPositionCode());
            object.put("pId", "");
            object.put("name", position.getPositionName());
            //String positionType = position.getPositionType();
            /*if("0".equals(positionType)){
            	object.put("name", position.getPositionName()+"(普通岗)");
            }else if("1".equals(positionType)){
            	object.put("name", position.getPositionName()+"(总部物料岗)");
            }else if("2".equals(positionType)){
            	object.put("name", position.getPositionName()+"(分部物料岗)");
            }else if("3".equals(positionType)){
            	object.put("name", position.getPositionName()+"(网点物料岗)");
            }*/
            
            //object.put("positionType", positionType);
            array.add(object);
        }
		return array.toString();
	}
	
	@RequestMapping(value="/getPositionTreeData/{userId}", produces="text/plain;charset=utf-8")
	@ResponseBody
	public String getPositionTreeData(@PathVariable(value = "userId") String userId, String fromType){
		HxUserPosition userPosition = new HxUserPosition();
		userPosition.setUserLoginName(userId);
		userPosition.setFromType(Integer.valueOf(fromType));
		List<HxPosition> positionList = hxUserPositionService.getPositionTreeData(userPosition);
		JSONArray array = new JSONArray();
	    for (HxPosition position : positionList) {
	         JSONObject object = new JSONObject();
	         object.put("id", position.getPositionCode());
	         array.add(object);
	     }
		return array.toString();
	}
	
	@RequestMapping(value="addUserPosition")
	@ResponseBody
	public String addUserPosition(HxUserPosition userPosition) {
        try {
        	String positionidTemp = userPosition.getPositionId();
        	String userloginnameTemp = userPosition.getUserLoginName();
        	Integer fromTypeTemp = userPosition.getFromType();
        	if (StringUtils.isNotEmpty(userloginnameTemp)) {
        		hxUserPositionService.deleteCompleteByUserLoginName(userPosition);
        	}
        	if (StringUtils.isNotEmpty(positionidTemp)) {
    			String[] positionidArr = positionidTemp.split(",");
    			for(int i = 0; i < positionidArr.length; i++){
    				HxUserPosition up = new HxUserPosition();
    				if(StringUtils.isNotEmpty(positionidArr[i])){
    					up.setPositionId(positionidArr[i]);
        				up.setUserLoginName(userloginnameTemp);
        				up.setFromType(fromTypeTemp);
        				hxUserPositionService.addUserPosition(up);
    				}
    			}
        	}
            return "{\"flag\" : \"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
}