package com.gome.gmhx.dao.storeinfo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;

@Repository("hxInoutStockDao")
public interface HxInoutStockDao {

	public List<Map<String, Object>> getInoutSotckHistoryPageList(Page page);
	
	public List<Map<String, Object>> getZbInStockHistoryPageList(Page page);
}
