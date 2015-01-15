package com.gome.gmhx.service.basicdata;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxFittingLocation;


public interface HxFittingLocationService {
	List<Map<String, Object>> getHxFittingLocationPageList(Page page);
	
	void addHxFittingLocation(HxFittingLocation hxFittingLocation);
	
	HxFittingLocation getHxFittingLocationById(String fittingId);
	
	void updateHxFittingLocation(HxFittingLocation hxFittingLocation);
	
	List<Map<String, Object>> getHxFittingLocationExport(HxFittingLocation hxFittingLocation);

	HxFittingLocation getShowById(String fittingId);
	
	
}
