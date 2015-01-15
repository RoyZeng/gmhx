package com.gome.gmhx.service.sysconfig;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HXPositionMapping;

public interface HxPositionMappingService {
	
	
	List<Map<String, Object>> getPositionMappingPageList(Page page);
	
	void addPositionMapping(HXPositionMapping positionMapping);

	void updatePositionMapping(HXPositionMapping positionMapping);

	List<HXPositionMapping> getPositionMappingByCodeAndName(HXPositionMapping positionMapping);

	void connect(HXPositionMapping positionMapping,String positionIds[],String type);

	void delete(String[] codesArray);

}
