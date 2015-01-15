package com.gome.gmhx.dao.servicemanage;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;

@Repository("hxInstallProjectServiceDao")
public interface HxInstallProjectServiceDao {

	List<Map<String, Object>> getServiceInstallReceiptPageList(Page page);

	Map<String, Object> selectInstallProjectById(String serviceId);

	void deleteServiceInstallProjectByPrimaryKey(String serviceId); 
	
}
