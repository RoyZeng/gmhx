package com.gome.gmhx.service.basicdata;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxCode;
import com.gome.gmhx.entity.HxOrganizationUnload;

public interface HxCodeService {
	
	List<HxCode> getAll();
	
	List<Map<String, Object>> getHxCodePageList(Page page);
	
	List<Map<String, Object>> getHxCodeSettingByCodeId(String codeId);
	
	void insertSetting(String codeId, HxCode hxCode);

	List<Map<String, String>> getOrgCombobox();

	List<Map<String, String>> getFittingMenu(String fittingPositionId);

	List<Map<String, String>> getWdOrgCombobox();

	List<Map<String, String>> getFbOrgCombobox();

	List<Map<String, String>> getZbOrgCombobox();

	List<Map<String, String>> getPositions(String userAccount);

	List<Map<String, String>> getFittingMenuNoPosition();

	List<Map<String, String>> getRoleCombobox();

	List<Map<String, String>> getECCombobox();

	List<Map<String, String>> getStoreCombobox(String center);

	List<Map<String, String>> getWebsiteCombobox(String serviceUnit);

	List<Map<String, String>> getAftermarketCombobox();

	List<Map<String, Object>> getModelCombobox(String category,String type);

	List<Map<String, Object>> getServiceUnit(String wd);

	Map<String, Object> getHxFittingByFittingCode(String fittingId);

	List<Map<String, Object>> getCategoryCombobox(String position);
	
	HxOrganizationUnload getOrganizationUnloadById(String id);

	List<Map<String, Object>> getFittingBySuit(String suitModel, String str);

	List<Map<String, String>> getBrandCombobox();

	List<Map> getRegionCombobox(String q);
	
	String getCategoryByModel(String model);
}
