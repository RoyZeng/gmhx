package com.gome.gmhx.service.materialmanage;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxParcelDelivery;


public interface HxParcelReceiptService {
	List<Map<String, Object>> getHxParcelReceiptPageList(Page page);
	
	void addHxParcelReceipt(HxParcelDelivery hxParcelReceipt);
	
	HxParcelDelivery getHxParcelReceiptById(String orgId);
	
	void updateHxParcelReceipt(HxParcelDelivery hxParcelReceipt);
	
	List<Map<String, Object>> getHxParcelReceiptExport(Map<String, Object> map);

	HxParcelDelivery getShowById(String orgId);
	
	void updateHxCurrentStock(HxParcelDelivery hxParcelReceipt);
	
	/**
	 * 网点、分部新料申请,总部新料调拨  邮包收货
	 * @param delivery
	 */
	void updateReceiveStock(HxParcelDelivery delivery);
	
}
