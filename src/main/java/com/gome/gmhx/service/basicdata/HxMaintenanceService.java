package com.gome.gmhx.service.basicdata;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxMaintenance;

public interface HxMaintenanceService {

    List<Map<String, Object>> getHxMaintenanceTree();
	
 	void createHxMaintenance(HxMaintenance maint);
	
	void updateHxMaintenance(Map<String, Object> map);
	 
	 List<Map<String, Object>> getHxMaintenanceExport(HxMaintenance maint); //导出

	 public Map<String,String> insertHxMaintenance(List<HxMaintenance> maint); //导入操作

	List<Map<String, Object>> getMaintenancePageList(Page page);  //分页

	void addHxMaintenance(HxMaintenance maint);

	HxMaintenance getHxMaintenanceByFaultCode(String fault_code);

	HxMaintenance getHxMaintenanceNoChangeByFaultCode(String fault_code);
	
	List<Map> getAllMaintenance(); 

	void modifyHxMaintenance(HxMaintenance maint);

}
