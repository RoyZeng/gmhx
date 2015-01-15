package com.gome.gmhx.service.sysconfig;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxOrganization;
import com.gome.gmhx.entity.HxOrganizationUnload;

public interface HxOrganizationService {
	
	List<Map<String, Object>> getOrganizationPageList(Page page);
	
	void addOrganization(HxOrganization organization);
	
	HxOrganization getOrganizationById(String orgCode);
	
	void updateOrganization(HxOrganization organization);

	List<Map<String, Object>> getOrganizationExport(HxOrganization organization);

	List<HxOrganization> getOrganizationTree();
	
	String getChild(String rootId);

	HxOrganization getOrganizationByPeripheralId(String orgCode);

	void delOrganizationById(String orgCode);
	
	void delOrganizationUnload(HxOrganizationUnload hxOrganizationUnload);
	
	List<Map<String, Object>> queryAllOrganizationList();
	
	List<Map<String, Object>> getAllOrganizationList();
	
	void addOrganizationUnload(HxOrganizationUnload hxOrganizationUnload);
	
	void updateOrganizationUnload(HxOrganizationUnload hxOrganizationUnload);
	
	List<Map<String, Object>> queryHxOrganizationList();
	
	List<Map<String, Object>> getHxOrganizationList();

	List<HxOrganization> getFittingStockTree();

	List<HxOrganization> getFittingStockTreeData(HxOrganization hxOrganization);

	void deleteCompleteByOrgId(HxOrganization hxOrganization);

	void addFittingStockPart(HxOrganization hxOrganizationTemp);

	Map<String, String> insertOrganizations(List<HxOrganization> organizations);
}
