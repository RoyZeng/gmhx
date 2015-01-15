package com.gome.gmhx.service.basicdata;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.HxGoodbill;
import com.gome.gmhx.entity.HxGoodbillKey;


public interface HxGoodbillService {
	public void insertGoodbill(HxGoodbill bill);

	public void deleteGoodbillByKey(HxGoodbillKey key);

	public void updateSale(HxGoodbill entity);

	/**
	 * 
	 * @param bills
	 * @return
	 */
	public Map<String, String> insertGoodbills(List<HxGoodbill> bills);

	public HxGoodbill getGoodbillByPrimaryKey(List<String> asList);

	/**
	 * 
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> getHxGoodbillPageList(Page page);

	public List<Map<String, Object>> getBillNumExport(HxGoodbill bill);

}
