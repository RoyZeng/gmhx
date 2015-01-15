package com.gome.gmhx.dao.orgmanage;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxLimit;

@Repository("hxLimitDao")
public interface HxLimitDao {
	
	List<Map<String, Object>> getLimitPageList(Page page);
	
	void addLimit(HxLimit limit);
	
	HxLimit getLimitById(String limitId);
	
	HxLimit getLimitByOrgId(String limitOrgId);
	
	/**
	 * 根据机构id查询原始的额度限制记录，初始化导入的额度
	 * @param orgId
	 * @return
	 */
	HxLimit getOriLimitByOrg(String orgId);
	
	/**
	 * 根据机构id查询最新的额度限制记录
	 * @param orgId
	 * @return
	 */
	HxLimit getLatestLimitByOrg(String orgId);
	
	void updateLimit(HxLimit limit);

	List<Map<String, Object>> getLimitHistoryPageList(Page page);
	
	/**根据Limit id更新额度记录*/
	void updateLimitById(HxLimit limit);
}
