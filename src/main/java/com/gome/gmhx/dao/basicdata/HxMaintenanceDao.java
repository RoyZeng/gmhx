package com.gome.gmhx.dao.basicdata;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxMaintenance;


@Repository("hxMaintenanceDao")
public interface HxMaintenanceDao {
	
	List<Map<String, Object>> getHxMaintenanceTree();  //树形结构
	
 	void createHxMaintenance(HxMaintenance maint);
	
	void updateHxMaintenance(Map<String, Object> map);
	
	public int insertHxMaintenance(List<HxMaintenance> maint);  //导入操作

	HxMaintenance selectHxMaintenancePrimaryKey(String arrangeNumber);
	
	 List<Map<String, Object>> getHxMaintenanceExport(HxMaintenance maint); //导出
   
	 List<Map<String, Object>> getMaintenancePageList(Page page);  //分页
	 
	void insertMaintenanceBatch(List<Object> list);

	void addHxMaintenance(HxMaintenance maint);

	HxMaintenance getHxMaintenanceByFaultCode(String fault_code);

	HxMaintenance getHxMaintenanceNoChangeByFaultCode(String fault_code);

	List<Map> getAllMaintenance();

	void modifyHxMaintenance(HxMaintenance maint);

}
