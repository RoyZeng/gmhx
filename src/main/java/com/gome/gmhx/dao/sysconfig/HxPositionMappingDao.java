package com.gome.gmhx.dao.sysconfig;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HXPositionMapping;

@Repository("hxPositionMappingDao")
public interface HxPositionMappingDao {
	
	List<Map<String, Object>> getPositionMappingPageList(Page page);
	
	void addPositionMapping(HXPositionMapping positionMapping);

	void updatePositionMapping(HXPositionMapping positionMapping);

	void getPositionByCodeAndName(HXPositionMapping positionMapping);

	void getOtherPositionByCodeAndName(HXPositionMapping positionMapping);

	void connect(HXPositionMapping positionMapping);

	void deletePMByCodeName(HXPositionMapping positionMapping);

	void updatePositionId(HXPositionMapping positionMapping);

	void delete(String code);

	void deletePMByPositionId(HXPositionMapping positionMapping);

	Integer selectPMByCodeName(HXPositionMapping positionMapping);
}
