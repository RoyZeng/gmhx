package com.gome.gmhx.dao.materialmanage;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxParcelDelivery;


@Repository("hxParcelReceiptDao")
public interface HxParcelReceiptDao {
	List<Map<String, Object>> getHxParcelReceiptPageList(Page page);
	
	void addHxParcelReceipt(HxParcelDelivery hxParcelReceipt);
	
	HxParcelDelivery getHxParcelReceiptById(String HxParcelDelivery);
	
	void updateHxParcelReceipt(HxParcelDelivery HxParcelDelivery);
	
	List<Map<String, Object>> getHxParcelReceiptExport(Map<String, Object> map);
	
	HxParcelDelivery getShowById(String orgId);

	void updateHxCurrentStock(HxParcelDelivery hxParcelReceipt);
}
