package com.gome.gmhx.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gome.gmhx.entity.HxUserUnload;
import com.gome.gmhx.service.sysconfig.HxUserService;

@Component
public class UnloadUser {
	@Resource
	private HxUserService hxUserService;
	
	public void unloadUser() throws Exception{
		//hxUserService.delUserUnload();
		List<Map<String, Object>> result = hxUserService.queryAllUserList();
		List<Map<String, Object>> data = hxUserService.getAllUserList();
		List<String> idList = new ArrayList<String>();
		List<String> iddataList = new ArrayList<String>();
		if(result!=null){
			if(result.size()>0){
				for(Map<String, Object> map : result){
					String userLoginName = (String) map.get("user_login_name");
					idList.add(userLoginName);
				}
			}
		}
		if(data!=null){
			if(data.size()>0){
				for(Map<String, Object> mapData : data){
					String userLoginName = (String) mapData.get("user_login_name");
					HxUserUnload hxUserUnload = new HxUserUnload();
					hxUserUnload.setUserLoginName(userLoginName);
					if(!idList.contains(userLoginName)){
						hxUserService.delUserUnload(hxUserUnload);
					}else{
						iddataList.add(userLoginName);
					}
				}
			}
		}
		if(result!=null){
			if(result.size()>0){
				for(Map<String, Object> map : result){
					String userLoginName = (String) map.get("user_login_name");
					String userName = (String) map.get("user_name");
					String userOrgId = (String) map.get("user_org_id");
					String userMobile = (String) map.get("user_mobile");
					String userEmail = (String) map.get("user_email");
					String userActive = (String) map.get("user_active");
					String userPhone = (String) map.get("user_phone");
					String userOrgName = (String) map.get("user_org_name");
					String orgParentId = (String) map.get("org_parent_id");
					Long fromTypeTemp  = (Long) map.get("fromType");
					String fromType = "";
					if(fromTypeTemp!=null){
						fromType = fromTypeTemp.toString();
					}
					HxUserUnload hxUserUnload = new HxUserUnload();
					hxUserUnload.setUserLoginName(userLoginName);
					hxUserUnload.setUserName(userName);
					hxUserUnload.setUserOrgId(userOrgId);
					hxUserUnload.setUserMobile(userMobile);
					hxUserUnload.setUserEmail(userEmail);
					hxUserUnload.setUserActive(userActive);
					hxUserUnload.setUserPhone(userPhone);
					hxUserUnload.setUserOrgName(userOrgName);
					hxUserUnload.setOrgParentId(orgParentId);
					hxUserUnload.setFromType(fromType);
					if(iddataList.contains(userLoginName)){
						hxUserService.updateUserUnload(hxUserUnload);
					}else{
						hxUserService.addUserUnload(hxUserUnload);
					}
				}
			}
		}
	}
}
