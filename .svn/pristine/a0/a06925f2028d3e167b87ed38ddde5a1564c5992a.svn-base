package com.gome.gmhx.service.wsdl.service;

import java.util.List;
import java.util.Map;

import com.gome.common.page.Page;
import com.gome.gmhx.entity.JlSale;
import com.gome.gmhx.entity.JlSaleKey;

public interface JlSaleService {
	
	public void insertSale(JlSale entity);
	
	public void deleteSaleById(JlSaleKey saleKey);
	
	public void updateSale(JlSale entity);
	
	public List<Map<String, Object>> querySale(Page page);
	
	public JlSale selectSaleByPrimaryKey(String gsdm,String thdh);

	public List<Map<String, Object>> getJlSaleDataPageList(Page page);

	public JlSale selectSaleByPK(JlSale sale);

	public List<Map<String, Object>> getJlSaleDataExport(JlSale sale);
	
}
