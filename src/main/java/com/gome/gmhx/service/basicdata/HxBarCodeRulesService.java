package com.gome.gmhx.service.basicdata;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxBarCodeRules;


public interface HxBarCodeRulesService {
	List<Map<String, Object>> getHxBarCodeRulesPageList(Page page);
	
	void addHxBarCodeRules(HxBarCodeRules hxBarCodeRules);
	
	HxBarCodeRules getHxBarCodeRulesById(String rulesId);
	
	void updateHxBarCodeRules(HxBarCodeRules hxBarCodeRules);
	
	List<Map<String, Object>> getHxBarCodeRulesExport(HxBarCodeRules hxBarCodeRules);

	HxBarCodeRules getShowById(String rulesId);
	
	
}
