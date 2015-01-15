package com.gome.gmhx.service.orgmanage;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxLimit;

public interface HxLimitService {
	
	List<Map<String, Object>> getLimitPageList(Page page);
	
	void addLimit(HxLimit limit);
	
	HxLimit getLimitById(String limitId);
	
	/** 关联初始化的记录主键 并增加一条记录*/
	void updateLimit(HxLimit limit);
	
	List<Map<String, Object>> getLimitHistoryPageList(Page page);

	HxLimit getOriLimitByOrg(String limitOrgId);
	
	HxLimit getLatestLimitByOrg(String orgId);
}
