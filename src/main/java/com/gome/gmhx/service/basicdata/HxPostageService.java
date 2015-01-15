package com.gome.gmhx.service.basicdata;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxPostage;

public interface HxPostageService {
	List<Map<String, Object>> getHxPostagePageList(Page page);
	
	void addHxPostage(HxPostage hxPostage);
	
	HxPostage getHxPostageById(String posId);
	
	void updateHxPostage(HxPostage hxPostage);
	
	List<Map<String, Object>> getHxPostageExport(HxPostage hxPostage);

	HxPostage getShowById(String posId);
	
	void deleteHxPostage(String posId);
	
	int validatePosNum(String posNum);
}
