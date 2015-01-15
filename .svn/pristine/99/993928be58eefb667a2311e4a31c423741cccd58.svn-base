package com.gome.gmhx.dao.basicdata;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxBarCodeRules;


@Repository("hxBarCodeRulesDao")
public interface HxBarCodeRulesDao {
	List<Map<String, Object>> getHxBarCodeRulesPageList(Page page);
	
	void addHxBarCodeRules(HxBarCodeRules hxBarCodeRules);
	
	HxBarCodeRules getHxBarCodeRulesById(String HxBarCodeRules);
	
	void updateHxBarCodeRules(HxBarCodeRules hxBarCodeRules);
	
	List<Map<String, Object>> getHxBarCodeRulesExport(HxBarCodeRules hxBarCodeRules);
	
	HxBarCodeRules getShowById(String rulesId);

	List<HxBarCodeRules> getHxBarCodeRulesByGomeCode(String gomeCode);
	
	

}
