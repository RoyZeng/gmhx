package com.gome.gmhx.dao.storeinfo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxCurrentStock;

@Repository("hxCurrentStockDao")
public interface HxCurrentStockDao {

	public List<Map<String, Object>> getHxCurrentStockPageList(Page page);
	
	public void updateHxCurrentStock(HxCurrentStock stock);
	
	/**
	 * 返回库存， 在途数量等信息
	 * @param stock
	 * @return
	 */
	public HxCurrentStock getCurrentStock(HxCurrentStock stock);
	
	public void insertStocksBatch(List<Object> list);

	public void deleteStock(HxCurrentStock object);

	public void insertCurrentStock(HxCurrentStock stock);

	public List<Map<String, Object>> exportBatchExcel(Map<String, Object> mapParam);

}
