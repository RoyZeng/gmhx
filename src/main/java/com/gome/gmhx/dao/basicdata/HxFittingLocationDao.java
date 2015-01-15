package com.gome.gmhx.dao.basicdata;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxFittingLocation;


@Repository("hxFittingLocationDao")
public interface HxFittingLocationDao {
	List<Map<String, Object>> getHxFittingLocationPageList(Page page);
	
	void addHxFittingLocation(HxFittingLocation hxFittingLocation);
	
	HxFittingLocation getHxFittingLocationById(String HxFittingLocation);
	
	void updateHxFittingLocation(HxFittingLocation hxFittingLocation);
	
	List<Map<String, Object>> getHxFittingLocationExport(HxFittingLocation hxFittingLocation);
	
	HxFittingLocation getShowById(String fittingId);
	
	void insertFittingLocationBatch(List<Object> list);
	

}
