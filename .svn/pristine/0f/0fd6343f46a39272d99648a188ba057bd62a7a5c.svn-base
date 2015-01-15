package com.gome.gmhx.service.basicdata.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.basicdata.HxMaintenanceDao;
import com.gome.gmhx.entity.HxMaintenance;
import com.gome.gmhx.service.basicdata.HxMaintenanceService;

@Service("HxMaintenanceService")
public class HxMaintenanceServiceImpl implements HxMaintenanceService{
	
	@Resource
	private HxMaintenanceDao  hxMaintenanceDao;

	@Override
	public List<Map<String, Object>> getHxMaintenanceTree() {
		return hxMaintenanceDao.getHxMaintenanceTree();
	}

	@Override
	public void createHxMaintenance(HxMaintenance maint) {
		hxMaintenanceDao.createHxMaintenance(maint);
		
	}

	@Override
	public void updateHxMaintenance(Map<String, Object> map) {
		hxMaintenanceDao.updateHxMaintenance(map);
		
	}

	@Override
	public List<Map<String, Object>> getHxMaintenanceExport(HxMaintenance maint) {
		
		return hxMaintenanceDao.getHxMaintenanceExport(maint);
	}

	@Override
	public Map<String, String> insertHxMaintenance(List<HxMaintenance> maint) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String exists = "{";
		String success = "{";
		String failure = "{";
		
		for(int i=0 ; i< maint.size() ; i++){
			HxMaintenance Maint1 = maint.get(i);
			HxMaintenance temp = hxMaintenanceDao.selectHxMaintenancePrimaryKey(Maint1.getArrangeNumber());
			if(temp!=null){
				exists +=(i+2)+",";
			}else{
				String code =Maint1.getClassifyCode().trim();
				String machineType = Maint1.getFaultCode().trim();
				if("".equals(code)||"".equals(machineType)){
					failure +=(i+2)+",";
				}else{
					try{
						hxMaintenanceDao.insertHxMaintenance(maint);
						success +=(i+2)+",";
					}catch(Exception e){
						failure +=(i+2)+",";
					}
				}
			}
		}
		resultMap.put("exists", exists.length()==1 ? exists+"}":exists.subSequence(0, exists.length()-1)+"}");
		resultMap.put("success", success.length()==1 ? success+"}":success.subSequence(0, success.length()-1)+"}");
		resultMap.put("failure", failure.length()==1 ? failure+"}":failure.subSequence(0, failure.length()-1)+"}");
		return resultMap;
	}


	@Override
	public List<Map<String, Object>> getMaintenancePageList(Page page) {
		return hxMaintenanceDao.getMaintenancePageList(page);
	}

	@Override
	public void addHxMaintenance(HxMaintenance maint) {
		hxMaintenanceDao.addHxMaintenance(maint);		
	}

	@Override
	public HxMaintenance getHxMaintenanceByFaultCode(String fault_code) {
		return hxMaintenanceDao.getHxMaintenanceByFaultCode(fault_code);
	}

	@Override
	public HxMaintenance getHxMaintenanceNoChangeByFaultCode(String fault_code) {
		return hxMaintenanceDao.getHxMaintenanceNoChangeByFaultCode(fault_code);
	}
	
	@Override
	public List<Map> getAllMaintenance() {
		// TODO Auto-generated method stub
		return hxMaintenanceDao.getAllMaintenance();
	}
	
	@Override
	public void modifyHxMaintenance(HxMaintenance maint) {
		hxMaintenanceDao.modifyHxMaintenance(maint);
	}
}
