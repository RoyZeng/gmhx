package com.gome.gmhx.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gome.gmhx.entity.HxUserUnload;
import com.gome.gmhx.service.sysconfig.HxUserService;

@Component
public class UnloadEmpUser {
	@Resource
	private HxUserService hxUserService;
	
	public void unloadEmpUser() throws Exception{
		List<Map<String, Object>> result = hxUserService.queryAllEmpUserList();
		List<Map<String, Object>> data = hxUserService.getAllEmpUserList();
		List<String> idList = new ArrayList<String>();
		List<String> iddataList = new ArrayList<String>();
		if(result!=null){
			if(result.size()>0){
				for(Map<String, Object> map : result){
					String positionId = (String) map.get("position_id");
					idList.add(positionId);
				}
			}
		}
		if(data!=null){
			if(data.size()>0){
				for(Map<String, Object> mapData : data){
					String positionId = (String) mapData.get("position_id");
					HxUserUnload hxUserUnload = new HxUserUnload();
					hxUserUnload.setPositionId(positionId);
					if(!idList.contains(positionId)){
						hxUserService.delEmpUserUnload(hxUserUnload);
					}else{
						iddataList.add(positionId);
					}
				}
			}
		}
		if(result!=null){
			if(result.size()>0){
				for(Map<String, Object> map : result){
					String userLoginName = (String) map.get("user_login_name");
					String positionId = (String) map.get("position_id");
					String fromType  = (String) map.get("from_type");
					HxUserUnload hxUserUnload = new HxUserUnload();
					hxUserUnload.setPositionId(positionId);
					hxUserUnload.setUserLoginName(userLoginName);
					hxUserUnload.setFromType(fromType);
					if(iddataList.contains(userLoginName)){
						hxUserService.updateEmpUserUnload(hxUserUnload);
					}else{
						hxUserService.addEmpUserUnload(hxUserUnload);
					}
				}
			}
		}
	}
}
