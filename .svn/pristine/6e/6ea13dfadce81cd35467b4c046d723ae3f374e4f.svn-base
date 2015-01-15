package com.gome.gmhx.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gome.gmhx.entity.HxOrganizationUnload;
import com.gome.gmhx.entity.HxUserUnload;
import com.gome.gmhx.service.sysconfig.HxOrganizationService;
import com.gome.gmhx.service.sysconfig.HxUserService;

@Component
public class SynchronizeOrganizationUser {
	@Resource
	private HxOrganizationService hxOrganizationService;
	
	@Resource
	private HxUserService hxUserService;
	
	public void synchronizeOrganizationUser() throws Exception{
		//同步恒信创建的组织机构
		List<Map<String, Object>> result = hxOrganizationService.queryHxOrganizationList();
		List<Map<String, Object>> data = hxOrganizationService.getHxOrganizationList();
		List<String> idList = new ArrayList<String>();
		List<String> iddataList = new ArrayList<String>();
		if(result!=null){
			if(result.size()>0){
				for(Map<String, Object> map : result){
					String idTemp = (String) map.get("id");
					idList.add(idTemp);
				}
			}
		}
		if(data!=null){
			if(data.size()>0){
				for(Map<String, Object> mapData : data){
					String idTemp = (String) mapData.get("id");
					HxOrganizationUnload hxOrganizationUnload = new HxOrganizationUnload();
					hxOrganizationUnload.setId(idTemp);
					if(!idList.contains(idTemp)){
						hxOrganizationService.delOrganizationUnload(hxOrganizationUnload);
					}else{
						iddataList.add(idTemp);
					}
				}
			}
		}
		
		
		if(result!=null){
			if(result.size()>0){
				for(Map<String, Object> map : result){
					String id = (String) map.get("id");
					String name = (String) map.get("name");
					String pid = (String) map.get("pid");
					String pname = (String) map.get("pname");
					String interfaceDm = (String) map.get("interface");
					HxOrganizationUnload hxOrganizationUnload = new HxOrganizationUnload();
					hxOrganizationUnload.setId(id);
					hxOrganizationUnload.setName(name);
					hxOrganizationUnload.setPid(pid);
					hxOrganizationUnload.setPname(pname);
					hxOrganizationUnload.setInterfaceDm(interfaceDm);
					if(iddataList.contains(id)){
						hxOrganizationService.updateOrganizationUnload(hxOrganizationUnload);
					}else{
						hxOrganizationService.addOrganizationUnload(hxOrganizationUnload);
					}
				}
			}
		}
	
		//同步恒信创建的用户
		List<Map<String, Object>> resultUser = hxUserService.queryHxUserList();
		List<Map<String, Object>> dataUser = hxUserService.getHxUserList();
		List<String> idListUser = new ArrayList<String>();
		List<String> iddataListUser = new ArrayList<String>();
		if(resultUser!=null){
			if(resultUser.size()>0){
				for(Map<String, Object> map : resultUser){
					String userLoginName = (String) map.get("user_login_name");
					idListUser.add(userLoginName);
				}
			}
		}
		if(dataUser!=null){
			if(dataUser.size()>0){
				for(Map<String, Object> mapData : dataUser){
					String userLoginName = (String) mapData.get("user_login_name");
					HxUserUnload hxUserUnload = new HxUserUnload();
					hxUserUnload.setUserLoginName(userLoginName);
					if(!idListUser.contains(userLoginName)){
						hxUserService.delUserUnload(hxUserUnload);
					}else{
						iddataListUser.add(userLoginName);
					}
				}
			}
		}
		if(resultUser!=null){
			if(resultUser.size()>0){
				for(Map<String, Object> map : resultUser){
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
					if(iddataListUser.contains(userLoginName)){
						hxUserService.updateUserUnload(hxUserUnload);
					}else{
						hxUserService.addUserUnload(hxUserUnload);
					}
				}
			}
		}
	}
}
