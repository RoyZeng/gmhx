package com.gome.gmhx.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gome.gmhx.entity.HxOrganizationUnload;
import com.gome.gmhx.service.sysconfig.HxOrganizationService;

@Component
public class UnloadOrganization {
	@Resource
	private HxOrganizationService hxOrganizationService;
	
	public void unloadOrganization() throws Exception{
		//hxOrganizationService.delOrganizationUnload();
		List<Map<String, Object>> result = hxOrganizationService.queryAllOrganizationList();
		List<Map<String, Object>> data = hxOrganizationService.getAllOrganizationList();
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
	}
}
